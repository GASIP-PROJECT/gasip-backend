package com.example.gasip.commentLikes.dto;

import com.example.gasip.commentLikes.model.CommentLikes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class CommentLikesRequestDto {
    private Long commentId;
    private Long postId;

    public static CommentLikesRequestDto toEntity(CommentLikes commentLikes) {
        return CommentLikesRequestDto.builder()
                .commentId(commentLikes.getComment().getCommentId())
                .postId(commentLikes.getBoard().getPostId())
                .build();
    }

}
