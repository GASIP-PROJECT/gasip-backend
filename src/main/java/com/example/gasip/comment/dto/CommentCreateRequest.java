package com.example.gasip.comment.dto;

import com.example.gasip.profboard.model.ProfBoard;
import com.example.gasip.comment.model.Comment;
import com.example.gasip.member.model.Member;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentCreateRequest{
    private String content;
    private Long parentId;

    public Comment toEntity(ProfBoard profBoard, Member member) {
        return Comment.builder()
                .profBoard(profBoard)
                .member(member)
                .content(content)
                .build();
    }
}
