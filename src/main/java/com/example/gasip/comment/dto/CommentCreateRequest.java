package com.example.gasip.comment.dto;

import com.example.gasip.board.model.Board;
import com.example.gasip.member.model.Member;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentCreateRequest{
    private String content;
    private Long parentId;

    public Comment toEntity(Board board,Member member) {
        return Comment.builder()
                .board(board)
                .member(member)
                .content(content)
                .commentLike(0L)
                .build();
    }
}
