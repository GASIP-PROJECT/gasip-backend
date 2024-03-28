package com.example.gasip.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberLogInRequest {

    @Schema(description = "이메일", example = "abc@gmail.com")
    private String email;
    @Schema(description = "비밀번호", example = "1234!@")
    private String password;
}
