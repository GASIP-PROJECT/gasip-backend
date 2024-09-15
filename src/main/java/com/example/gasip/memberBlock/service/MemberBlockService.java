package com.example.gasip.memberBlock.service;

import com.example.gasip.board.repository.BoardRepository;
import com.example.gasip.global.constant.ErrorCode;
import com.example.gasip.global.exception.MemberNotFoundException;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.member.model.Member;
import com.example.gasip.member.repository.MemberRepository;
import com.example.gasip.memberBlock.dto.MemberBlockRequest;
import com.example.gasip.memberBlock.dto.MemberBlockResponse;
import com.example.gasip.memberBlock.model.MemberBlock;
import com.example.gasip.memberBlock.repository.MemberBlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberBlockService {
    private final MemberBlockRepository memberBlockRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public MemberBlockResponse blockMember(MemberBlockRequest memberBlockRequest, MemberDetails memberDetails) {
        Member blocker = memberRepository.findById(memberDetails.getId()).orElseThrow(
                () -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER));
        Member blocked = memberRepository.findById(memberBlockRequest.getBlocked()).orElseThrow(
                () -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER));

        MemberBlock memberBlock = MemberBlock.builder()
                .blocker(blocker)
                .blocked(blocked)
                .build();

        memberBlockRepository.save(memberBlock);

        return MemberBlockResponse.formEntity(memberBlock);
    }
}
