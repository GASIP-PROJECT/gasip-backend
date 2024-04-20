package com.example.gasip.likes.dto;

import com.example.gasip.likes.model.Likes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class LikeResponseDto {
    private Long likesId;
    private Long postId;
    private Long memberId;

    public static LikeResponseDto fromEntity(Likes likes) {
        return LikeResponseDto.builder()
                .likesId(likes.getLikesId())
                .postId(likes.getBoard().getPostId())
                .memberId(likes.getMember().getMemberId())
                .build();
    }
}
