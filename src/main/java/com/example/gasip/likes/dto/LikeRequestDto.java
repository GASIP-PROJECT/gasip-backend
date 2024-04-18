package com.example.gasip.likes.dto;

import com.example.gasip.likes.model.Likes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class LikeRequestDto {
    private Long postId;

    public LikeRequestDto(Long postId) {
        this.postId = postId;
    }

    public static LikeRequestDto fromEntity(Likes likes) {
        return LikeRequestDto.builder()
                .postId(likes.getBoard().getPostId())
                .build();
    }
}
