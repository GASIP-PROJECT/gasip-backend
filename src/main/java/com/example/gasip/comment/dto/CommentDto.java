package com.example.gasip.comment.dto;

import com.example.gasip.board.model.Board;
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
public class CommentDto implements Serializable {
//    private Long postId;
    private Long commentId;
    private String content;
    private String writer;
    private Long commentLike;
    private List<CommentDto> commentChildren = new ArrayList<>();

    public CommentDto(Long commentId, String content, String writer) {
        this.commentId = commentId;
        this.content = content;
        this.writer = writer;
    }

    //fromEntity메서드 개발
    public static CommentDto ToEntity(Comment comment) {
        return CommentDto.builder()
//                .board(comment.getBoard().getPostId())
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .writer(comment.getWriter())
                .commentChildren(comment.getCommentChildren().stream().map(CommentDto::ToEntity).collect(Collectors.toList()))
                .build();
//        return CommentCreateResponse.builder()
//                .regDate(comment.getRegDate())
//                .updateDate(comment.getUpdateDate())
//                .commentId(comment.getCommentId())
//                .board(comment.getBoard())
//                .writer(comment.getWriter())
//                .content(comment.getContent())
//                .commentChildren(comment.getCommentChildren().stream().map(CommentCreateResponse::fromEntity).collect(Collectors.toList()))
//                .build();
    }
}
