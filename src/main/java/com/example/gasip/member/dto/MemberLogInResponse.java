package com.example.gasip.member.dto;

import com.example.gasip.member.model.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class MemberLogInResponse {

    @Schema(description = "memberId(PK)", example = "1")
    private Long memberId;
    @Schema(description = "이메일", example = "abc@gmail.com")
    private String email;
    @Schema(description = "이름", example = "이름")
    private String name;
    @Schema(description = "닉네임", example = "무한이")
    private String nickname;
    @Schema(description = "JWT TOKEN", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxNCIsImVtYWlsIjoiYXBpZG9jQGVtYWlsLmNvbSIsInJvbGVzIjpbIk1FTUJFUiJdLCJleHAiOjE3MTIyMjQ5NzV9.SQKtyHmqn2NKzHy4BX7_IgBePO_svEtmz1xbO9ToMz8")
    private String accessToken;

    public static MemberLogInResponse fromEntity(Member member,String token) {
        return MemberLogInResponse.builder()
            .memberId(member.getMemberId())
            .email(member.getEmail())
            .name(member.getName())
            .nickname(member.getNickname())
            .accessToken(token)
            .build();
    }
}
