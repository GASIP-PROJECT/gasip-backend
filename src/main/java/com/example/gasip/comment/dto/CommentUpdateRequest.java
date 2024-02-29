package com.example.gasip.comment.dto;

import com.example.gasip.profboard.model.ProfBoard;
import com.example.gasip.comment.model.Comment;
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

    public Comment toEntity(ProfBoard profBoard, Member member) {
        return Comment.builder()
            .profBoard(profBoard)
            .member(member)
            .content(content)
            .build();
    }
}
