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
    private String writer;
    private Long commentLike;
    private Long parentId;
    private List<CommentDto> commentChildren = new ArrayList<>();

    public static CommentCreateResponse fromEntity(Comment comment) {
        // 부모댓글이 있는 경우
        if (comment.getParentComment() != null) {
            return CommentCreateResponse.builder()
                .postId(comment.getBoard().getPostId())
                .memberId(comment.getMember().getMemberId())
                .content(comment.getContent())
                .writer(comment.getWriter())
                .parentId(comment.getParentComment().getCommentId())
                .build();
        }
        // 부모댓글이 없는 경우
        else {
            return CommentCreateResponse.builder()
                .postId(comment.getBoard().getPostId())
                .memberId(comment.getMember().getMemberId())
                .content(comment.getContent())
                .writer(comment.getWriter())
                .build();
        }

    }
}
