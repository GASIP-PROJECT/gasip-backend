package com.example.gasip.comment.dto;

import com.example.gasip.comment.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CommentChildrenReadResponse implements Serializable {
    private Long postId;
    private Long commentId;
    private Long memberId;
    private String content;
    private Long commentLike;
    private Long parentId;

    public static CommentChildrenReadResponse fromEntity(Comment comment) {
        return buildCommentDtoWithParentId(comment);
    }
    private static CommentChildrenReadResponse buildCommentDtoWithParentId(Comment comment) {

        return CommentChildrenReadResponse.builder()
            .postId(comment.getBoard().getPostId())
            .commentId(comment.getCommentId())
            .memberId(comment.getMember().getMemberId())
            .content(comment.getContent())
            .parentId(comment.getParentComment().getCommentId())
            .build();
    }
}
