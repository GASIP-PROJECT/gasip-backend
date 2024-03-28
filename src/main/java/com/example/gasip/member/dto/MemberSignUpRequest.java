package com.example.gasip.member.dto;

import com.example.gasip.global.constant.Role;
import com.example.gasip.member.model.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSignUpRequest {
    @NotBlank
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    @Schema(description = "이메일", example = "abc@gmail.com")
    private String email;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}")
    @Schema(description = "비밀번호", example = "1234!@")
    private String password;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z가-힣]{2,16}$")
    @Schema(description = "닉네임", example = "무한이")
    private String name;

    public Member toEntity(MemberSignUpRequest memberSignUpRequest) {
        return Member.builder()
            .email(memberSignUpRequest.email)
            .password(memberSignUpRequest.password)
            .name(memberSignUpRequest.name)
            .role(Role.MEMBER)
            .build();

    }
}
