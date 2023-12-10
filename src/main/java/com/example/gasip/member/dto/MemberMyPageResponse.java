package com.example.gasip.member.dto;

import com.example.gasip.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberMyPageResponse {
    private String email;
    private String name;

    public static MemberMyPageResponse fromEntity(Member member) {
        return MemberMyPageResponse.builder()
            .email(member.getEmail())
            .name(member.getName())
            .build();
    }

}
