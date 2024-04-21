package com.example.gasip.comment.dto;

import com.example.gasip.board.model.Board;
import com.example.gasip.member.model.Member;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentUpdateRequest {

    @NotNull
    private String content;

    public Comment toEntity(Board board, Member member) {
        return Comment.builder()
            .board(board)
            .member(member)
            .content(content)
            .build();
    }
}
