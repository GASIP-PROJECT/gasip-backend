package com.example.gasip.member.dto;

import com.example.gasip.member.model.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberMyPageResponse {
    @Schema(description = "이메일", example = "abc@gmail.com")
    private String email;
    @Schema(description = "닉네임", example = "무한이")
    private String name;
    private String nickname;

    public static MemberMyPageResponse fromEntity(Member member) {
        return MemberMyPageResponse.builder()
            .email(member.getEmail())
            .name(member.getName())
            .nickname(member.getNickname())
            .build();
    }

}
