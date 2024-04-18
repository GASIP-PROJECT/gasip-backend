package com.example.gasip.likes.dto;

import com.example.gasip.likes.model.Likes;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class LikeRequestDto {
    private Long postId;

    @QueryProjection
    public LikeRequestDto(Long postId) {
        this.postId = postId;
    }

    public static LikeRequestDto fromEntity(Likes likes) {
        return LikeRequestDto.builder()
                .postId(likes.getBoard().getPostId())
                .build();
    }
}
