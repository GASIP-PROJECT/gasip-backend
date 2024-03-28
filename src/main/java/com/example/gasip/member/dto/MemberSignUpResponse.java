package com.example.gasip.member.dto;

import com.example.gasip.member.model.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSignUpResponse {
    @Schema(description = "id", example = "1")
    private Long id;
    @Schema(description = "이메일", example = "abc@gmail.com")
    private String email;
    @Schema(description = "닉네임", example = "무한이")
    private String name;

    @Builder
    public MemberSignUpResponse(Long id,String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public static MemberSignUpResponse fromEntity(Member member) {
        return MemberSignUpResponse.builder()
            .id(member.getMemberId())
            .email(member.getEmail())
            .name(member.getName())
            .build();

    }
}
