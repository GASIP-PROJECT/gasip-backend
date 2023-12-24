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

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CommentDto implements Serializable {
    private Long postId;
    private Long memberId;
    private String content;
    private String writer;
    private Long commentLike;
    private Long parentId;
    private List<CommentDto> commentChildren = new ArrayList<>();

    public CommentDto(Long commentId, String content, String writer) {
        this.content = content;
        this.writer = writer;
    }

    //fromEntity메서드 개발
    public static CommentDto fromEntity(Comment comment) {
        if (comment.getParentComment() != null) {
            return CommentDto.builder()
                .postId(comment.getBoard().getPostId())
                .memberId(comment.getMember().getMemberId())
                .content(comment.getContent())
                .writer(comment.getWriter())
                .parentId(comment.getParentComment().getCommentId())
//                .commentChildren(comment.getCommentChildren().stream().map(CommentDto::fromEntity).collect(Collectors.toList()))
                .build();
        } else {
            return CommentDto.builder()
                .postId(comment.getBoard().getPostId())
                .memberId(comment.getMember().getMemberId())
                .content(comment.getContent())
                .writer(comment.getWriter())
                .build();
        }

    }
}
