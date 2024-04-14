package com.example.gasip.member.dto;

import com.example.gasip.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class MemberUpdatePasswordResponse {

    String password;

    public static MemberUpdatePasswordResponse fromEntity(Member member) {
        return MemberUpdatePasswordResponse.builder()
            .password(member.getPassword())
            .build();
    }
}
