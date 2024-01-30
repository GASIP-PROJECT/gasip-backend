package com.example.gasip.likes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeRequestDto {
    private Long memberId;
    private Long postId;

    public LikeRequestDto(Long memberId, Long postId) {
        this.memberId = memberId;
        this.postId = postId;
    }
}
