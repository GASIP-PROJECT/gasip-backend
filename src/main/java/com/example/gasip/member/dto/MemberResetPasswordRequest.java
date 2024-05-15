package com.example.gasip.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResetPasswordRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
