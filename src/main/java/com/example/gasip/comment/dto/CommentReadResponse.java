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

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CommentReadResponse implements Serializable {
    private Long postId;
    private Long memberId;
    private String content;
    private Long commentLike;
    private Long parentId;
    private List<CommentReadResponse> commentChildren = new ArrayList<>();

    public static CommentReadResponse fromEntity(Comment comment) {
        // 부모댓글이 있는 경우
        if (comment.getParentComment() != null) {
            return buildCommentDtoWithParentId(comment);
        }
        // 부모댓글이 없는 경우
        else {
            return buildCommentDtoWithChildrenComment(comment);
        }

    }
    private static CommentReadResponse buildCommentDtoWithParentId(Comment comment) {
        return CommentReadResponse.builder()
            .postId(comment.getBoard().getPostId())
            .memberId(comment.getMember().getMemberId())
            .content(comment.getContent())
            .parentId(comment.getParentComment().getCommentId())
            .build();
    }
    private static CommentReadResponse buildCommentDtoWithChildrenComment(Comment comment) {
        return CommentReadResponse.builder()
            .postId(comment.getBoard().getPostId())
            .memberId(comment.getMember().getMemberId())
            .content(comment.getContent())
            .commentChildren(comment.getCommentChildren().stream().map(CommentReadResponse::fromEntity).collect(Collectors.toList()))
            .build();
    }
}
