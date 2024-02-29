package com.example.gasip.comment.dto;

import com.example.gasip.comment.model.Comment;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CommentCreateResponse {
    private Long postId;
    private Long memberId;
    private String content;
    private Long commentLike;
    private Long parentId;
    private List<CommentReadResponse> commentChildren = new ArrayList<>();

    public static CommentCreateResponse fromEntity(Comment comment) {
        // 부모댓글이 있는 경우
        if (comment.getParentComment() != null) {
            return buildCommentDtoWithParentId(comment);
        }
        // 부모댓글이 없는 경우
        else {
            return buildCommentDtoWithoutParentId(comment);
        }

    }
    private static CommentCreateResponse buildCommentDtoWithParentId(Comment comment) {
        return CommentCreateResponse.builder()
            .postId(comment.getProfBoard().getPostId())
            .memberId(comment.getMember().getMemberId())
            .content(comment.getContent())
            .parentId(comment.getParentComment().getCommentId())
            .build();
    }
    private static CommentCreateResponse buildCommentDtoWithoutParentId(Comment comment) {
        return CommentCreateResponse.builder()
            .postId(comment.getProfBoard().getPostId())
            .memberId(comment.getMember().getMemberId())
            .content(comment.getContent())
            .build();
    }
}
