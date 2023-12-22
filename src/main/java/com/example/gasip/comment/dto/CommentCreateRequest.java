package com.example.gasip.comment.dto;

import com.example.gasip.board.model.Board;
import com.example.gasip.comment.model.Comment;
import com.example.gasip.common.BaseTimeEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateRequest{
    private Long postId;
    private String content;
    private String writer;
    private Long parentId;

//    public Comment toEntity(CommentCreateRequest commentCreateRequest) {
//        return Comment.builder()
//                .board(commentCreateRequest.getBoard())
//                .writer(commentCreateRequest.getWriter())
//                .content(commentCreateRequest.getContent())
//                .commentChildren(commentCreateRequest.getCommentChildren().stream().map(CommentCreateRequest::toEntity).collect(Collectors.toList()))
//                .build();
//    }
}
