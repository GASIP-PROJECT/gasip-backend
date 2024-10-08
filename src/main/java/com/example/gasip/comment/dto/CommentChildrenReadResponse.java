package com.example.gasip.comment.dto;

import com.example.gasip.board.model.ContentActivity;
import com.example.gasip.comment.model.Comment;
import com.example.gasip.global.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CommentChildrenReadResponse extends BaseTimeEntity implements Serializable {
    private Long postId;
    private Long commentId;
    private Long memberId;
    private String memberName;
    private String content;
    private Long commentLike;
    private Boolean isCommentLike;
    private Long parentId;
    private String nickName;
    private ContentActivity contentActivity;

    public static CommentChildrenReadResponse fromEntity(Comment comment) {
        return CommentChildrenReadResponse.builder()
            .regDate(comment.getRegDate())
            .updateDate(comment.getUpdateDate())
            .postId(comment.getBoard().getPostId())
            .commentId(comment.getCommentId())
            .memberId(comment.getMember().getMemberId())
            .memberName(comment.getMember().getName())
            .content(comment.getContent())
            .commentLike(comment.getCommentLike())
            .isCommentLike(comment.getIsCommentLike())
            .parentId(comment.getParentComment().getCommentId())
            .nickName(comment.getMember().getNickname())
            .contentActivity(comment.getContentActivity())
            .build();
    }
}
