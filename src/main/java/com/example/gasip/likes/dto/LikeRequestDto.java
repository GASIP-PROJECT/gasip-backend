package com.example.gasip.likes.dto;

import com.example.gasip.likes.model.Likes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class LikeRequestDto {
    private Long memberId;
    private Long postId;

    public LikeRequestDto(Long memberId, Long postId) {
        this.memberId = memberId;
        this.postId = postId;
    }

    public static LikeRequestDto fromEntity(Likes likes) {
        return LikeRequestDto.builder()
                .memberId(likes.getMember().getMemberId())
                .postId(likes.getBoard().getPostId())
                .build();
    }
}
