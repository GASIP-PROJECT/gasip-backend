package com.example.gasip.member.dto;

import com.example.gasip.member.model.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class MemberUpdateNicknameResponse {

    String nickname;

    public static MemberUpdateNicknameResponse fromEntity(Member member) {
        return MemberUpdateNicknameResponse.builder()
            .nickname(member.getNickname())
            .build();
    }
}
