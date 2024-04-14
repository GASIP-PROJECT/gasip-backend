package com.example.gasip.member.service;

import com.auth0.jwt.JWT;
import com.example.gasip.board.repository.BoardRepository;
import com.example.gasip.global.constant.ErrorCode;
import com.example.gasip.global.exception.MemberDuplicatedException;
import com.example.gasip.global.exception.MemberNotFoundException;
import com.example.gasip.global.security.JwtService;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.member.dto.*;
import com.example.gasip.member.model.Member;
import com.example.gasip.member.repository.MemberRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
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
    public List<?> getBoards(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
            (IllegalArgumentException::new)
        );
        return boardRepository.findContentsByMemberId(member.getMemberId());
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

    private void validateEmailDuplicated(MemberSignUpRequest memberSignUpRequest) {
        memberRepository.findByEmail(memberSignUpRequest.getEmail()).ifPresent(member -> {
                throw new IllegalArgumentException();
            }
        );
    }
    @Transactional
    public String sendCodeToEmail(String email) {
        checkDuplicatedEmail(email);
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
            throw new MemberDuplicatedException(ErrorCode.DUPLICATE_MEMBER);
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
            log.debug("MemberService.createCode() exception occur");
            throw new IllegalArgumentException();
        }
    }
    @Transactional
    public String verifiedCode(String email, String authCode) {
        this.checkDuplicatedEmail(email);
        String redisAuthCode = redisMailService.getValues(AUTH_CODE_PREFIX + email);
        if (redisAuthCode.equals(authCode)) {
            return "코드가 정상 통과되었습니다.";
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Transactional
    public MemberUpdateNicknameResponse updateNickname(MemberDetails memberDetails,MemberUpdateNicknameRequest memberUpdateNicknameRequest) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(
            () -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER)
        );
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

    public String withdrawAccount(MemberDetails memberDetails) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(
            () -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER)
        );
        memberRepository.delete(member);
        return "회원 탈퇴 성공";
    }
}
