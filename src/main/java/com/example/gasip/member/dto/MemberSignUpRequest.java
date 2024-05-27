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
    @Pattern(regexp = "^[a-zA-Z가-힣]{2,12}$")
    @Schema(description = "이름", example = "정혜민")
    private String name;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z가-힣]{2,12}$")
    @Schema(description = "닉네임", example = "가십이")
    private String nickname;

    public Member toEntity() {
        return Member.builder()
            .email(email)
            .password(password)
            .name(name)
            .nickname(nickname)
            .role(Role.MEMBER)
            .isWithDraw(false)
            .build();

    }
}
