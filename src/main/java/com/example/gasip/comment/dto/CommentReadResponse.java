package com.example.gasip.comment.dto;

import com.example.gasip.comment.model.Comment;
import com.example.gasip.global.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CommentReadResponse extends BaseTimeEntity implements Serializable {
    private Long postId;
    private Long commentId;
    private Long memberId;
    private String memberName;
    private String content;
    private Long commentLike;
    private Long parentId;
    private List<CommentChildrenReadResponse> commentChildren = new ArrayList<>();

    public static CommentReadResponse fromEntity(Comment comment) {
        // 부모 댓글인 경우
        if (comment.getParentComment() == null) {
            return buildCommentDtoWithChildrenComment(comment);
        }
        // 자식 댓글인 경우
        else {
            return null;
        }
    }
    private static CommentReadResponse buildCommentDtoWithChildrenComment(Comment comment) {
        return CommentReadResponse.builder()
            .regDate(comment.getRegDate())
            .updateDate(comment.getUpdateDate())
            .postId(comment.getBoard().getPostId())
            .commentId(comment.getCommentId())
            .memberId(comment.getMember().getMemberId())
            .memberName(comment.getMember().getName())
            .content(comment.getContent())
            .commentChildren(comment.getCommentChildren()
                .stream()
                .map(CommentChildrenReadResponse::fromEntity)
                .collect(Collectors.toList()))
            .build();
    }
}
