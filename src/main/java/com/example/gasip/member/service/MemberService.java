package com.example.gasip.member.service;

import com.auth0.jwt.JWT;
import com.example.gasip.board.dto.BoardReadResponse;
import com.example.gasip.board.model.Board;
import com.example.gasip.board.repository.BoardRepository;
import com.example.gasip.global.constant.ErrorCode;
import com.example.gasip.global.exception.*;
import com.example.gasip.global.security.JwtService;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.likes.repository.LikeRepository;
import com.example.gasip.member.dto.*;
import com.example.gasip.member.model.Member;
import com.example.gasip.member.repository.MemberRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationProvider authenticationProvider;
    private final JwtService jwtService;
    private final BoardRepository boardRepository;
    private final MailService mailService;
    private final RedisMailService redisMailService;

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;
    private static final String AUTH_CODE_PREFIX = "AuthCode ";

    @Transactional
    public MemberSignUpResponse signup(MemberSignUpRequest memberSignUpRequest) {
        validateEmailDuplicated(memberSignUpRequest);
        validateNickNameDuplicated(memberSignUpRequest.getNickname());
        Member member = memberSignUpRequest.toEntity();
        member.encodePassword(passwordEncoder);

        return MemberSignUpResponse.fromEntity(memberRepository.save(member));
    }

    @Transactional
    public MemberLogInResponse login(MemberLogInRequest memberLogInRequest) {
        Authentication authentication = authenticationProvider.authenticate(
            new UsernamePasswordAuthenticationToken(memberLogInRequest.getEmail(), memberLogInRequest.getPassword())
        );
        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
        Member member = memberRepository.getReferenceById(memberDetails.getId());
        if (Boolean.TRUE.equals(member.getIsWithDraw())) {
            throw new MemberNotFoundException(ErrorCode.WITHDRAWED_MEMBER);
        }
        String accessToken = jwtService.issue(member.getMemberId(), member.getEmail(), member.getRole());
        return MemberLogInResponse.fromEntity(member,accessToken);
    }

    @Transactional(readOnly = true)
    public MemberMyPageResponse getMyPage(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
            (IllegalArgumentException::new)
        );
        return MemberMyPageResponse.fromEntity(member);
    }

    @Transactional(readOnly = true)
    public List<BoardReadResponse> getMyBoards(MemberDetails memberDetails, Pageable pageable) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(
            () -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER)
        );
        List<BoardReadResponse> boardReadResponseList = boardRepository.findAllByMemberId(memberDetails.getId(),pageable);
        List<BoardReadResponse> boardReadResponses = new ArrayList<>();
        for (BoardReadResponse boardReadResponse : boardReadResponseList) {
            Board board = boardRepository.getReferenceById(boardReadResponse.getPostId());
            if (likeRepository.findByMemberAndBoard(member, board).isEmpty()) {
                board.updateLike(false);
            } else {
                board.updateLike(true);
            }
            boardReadResponses.add(BoardReadResponse.fromEntity(board));
        }
        return boardReadResponses;
    }

    @Transactional
    public Boolean isAuthenticated(String accessToken) {
        String token = accessToken.substring(7);
        Date expiresAt = JWT.decode(token).getExpiresAt();
        if (expiresAt.after(Date.from(Instant.now()))) {
            return true;
        } else {
            return false;
        }
    }
    @Transactional
    public String sendCodeToEmail(String email) {
        checkDuplicatedEmail(email);
        String authCode = createCode();
//        String authCode = String.valueOf(315123);
        try {
            mailService.sendEmail(email, authCode);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "AuthCode " + Email / value = AuthCode )
        redisMailService.setValues(AUTH_CODE_PREFIX + email,
            authCode, Duration.ofMillis(authCodeExpirationMillis));

        return "이메일을 정상적으로 전송했습니다.";
    }

    @Transactional
    public String sendCodeToExistedEmail(String email) {
        String authCode = createCode();
        try {
            mailService.sendEmail(email, authCode);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "AuthCode " + Email / value = AuthCode )
        redisMailService.setValues(AUTH_CODE_PREFIX + email,
            authCode, Duration.ofMillis(authCodeExpirationMillis));

        return "이메일을 정상적으로 전송했습니다.";
    }

    private void checkDuplicatedEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            throw new DuplicateMemberException(ErrorCode.DUPLICATE_MEMBER);
        }
    }

    private String createCode() {
        int lenth = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < lenth; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            log.debug("인증번호가 발급되지 않았습니다.");
            throw new NoSuchVerifiedCodeException(ErrorCode.NO_SUCH_CODE);
        }
    }
    @Transactional
    public String verifiedCode(String email, String authCode) {
        String redisAuthCode = redisMailService.getValues(AUTH_CODE_PREFIX + email);
        if (redisAuthCode.equals(authCode)) {
            return "코드가 정상 통과되었습니다.";
        } else {
            throw new InvalidVerifiedException(ErrorCode.INVALID_CODE);
        }
    }
    @Transactional
    public MemberUpdateNicknameResponse updateNickname(MemberDetails memberDetails,MemberUpdateNicknameRequest memberUpdateNicknameRequest) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(
            () -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER)
        );
        validateNickNameDuplicated(memberUpdateNicknameRequest.getNickname());
        member.updateNickname(memberUpdateNicknameRequest.getNickname());
        return MemberUpdateNicknameResponse.fromEntity(member);
    }
    @Transactional
    public MemberUpdatePasswordResponse updatePassword(MemberDetails memberDetails, MemberUpdatePasswordRequest memberUpdatePasswordRequest) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(
            () -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER)
        );
        member.updatePassword(memberUpdatePasswordRequest.getPassword());
        member.encodePassword(passwordEncoder);
        return MemberUpdatePasswordResponse.fromEntity(member);
    }
    @Transactional
    public String withdrawAccount(MemberDetails memberDetails) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(
            () -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER)
        );
        member.updateMemberWithdrawalStatus(true);
        return "회원 탈퇴 성공";
    }

    @Transactional
    public String restoreAccount(MemberDetails memberDetails) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(
            () -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER)
        );
        member.updateMemberWithdrawalStatus(false);
        return "회원 복구 성공";
    }
    @Transactional
    public MemberResetPasswordResponse resetPassword(MemberResetPasswordRequest memberResetPasswordRequest) {
        Member member = memberRepository.findByEmail(memberResetPasswordRequest.getEmail()).orElseThrow(
            () -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER)
        );
        member.updatePassword(memberResetPasswordRequest.getPassword());
        member.encodePassword(passwordEncoder);
        return MemberResetPasswordResponse.fromEntity(member);
    }

    private void validateEmailDuplicated(MemberSignUpRequest memberSignUpRequest) {
        memberRepository.findByEmail(memberSignUpRequest.getEmail()).ifPresent(member -> {
                throw new DuplicateMemberException(ErrorCode.DUPLICATE_MEMBER);
            }
        );
    }
    private void validateNickNameDuplicated(String nickName) {
        memberRepository.findByNickname(nickName).ifPresent(member -> {
                throw new DuplicateNickNameException(ErrorCode.DUPLICATE_NICKNAME);
            }
        );
    }
}
