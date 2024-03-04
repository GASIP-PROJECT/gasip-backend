package com.example.gasip.freefeed.service;

import com.example.gasip.freefeed.dto.FeedCreateRequest;
import com.example.gasip.freefeed.dto.FeedCreateResponse;
import com.example.gasip.freefeed.model.Freefeed;
import com.example.gasip.freefeed.repository.FreefeedRepository;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.member.model.Member;
import com.example.gasip.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FreefeedService {
    private final FreefeedRepository freefeedRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public FeedCreateResponse createFeed(FeedCreateRequest feedCreateRequest, MemberDetails memberDetails) {
        Member member = memberRepository.getReferenceById(memberDetails.getId());
        Freefeed freefeed = freefeedRepository.save(feedCreateRequest.toEntity(member));
        return FeedCreateResponse.fromEntity(freefeed);
    }
}
