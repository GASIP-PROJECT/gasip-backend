package com.example.gasip.member.dto;

import com.example.gasip.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class MemberResetPasswordResponse {
    String email;
    String password;

    public static MemberResetPasswordResponse fromEntity(Member member) {
        return MemberResetPasswordResponse.builder()
            .email(member.getEmail())
            .password(member.getPassword())
            .build();
    }
}
