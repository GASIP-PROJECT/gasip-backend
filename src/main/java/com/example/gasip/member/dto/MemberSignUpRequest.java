package com.example.gasip.member.dto;

import com.example.gasip.global.constant.Role;
import com.example.gasip.member.model.Member;
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
    private String email;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}")
    private String password;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z가-힣]{2,16}$")
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
