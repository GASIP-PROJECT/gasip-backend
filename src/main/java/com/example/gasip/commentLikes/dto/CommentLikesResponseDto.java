package com.example.gasip.commentLikes.dto;

import com.example.gasip.commentLikes.model.CommentLikes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class CommentLikesResponseDto {
    private Long commentLikesId;
    private Long commentId;
    private Long postId;
    private Long memberId;

    public static CommentLikesResponseDto fromEntity(CommentLikes commentLikes) {
        return CommentLikesResponseDto.builder()
                .commentLikesId(commentLikes.getLikesId())
                .commentId(commentLikes.getComment().getCommentId())
                .postId(commentLikes.getBoard().getPostId())
                .memberId(commentLikes.getMember().getMemberId())
                .build();
    }
}
