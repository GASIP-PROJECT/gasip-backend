package com.example.gasip.board.dto;

import com.example.gasip.common.BaseTimeEntity;
import com.example.gasip.board.model.Board;
import com.example.gasip.professor.model.Professor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreateRequest extends BaseTimeEntity {

    @NotNull
    private String content;
    private Long clickCount;
    private Long likeCount;
//    @NotNull
    private Professor professor;


    public Board toEntity(BoardCreateRequest boardCreateRequest) {
        return Board.builder()
                .content(boardCreateRequest.getContent())
                .clickCount(boardCreateRequest.getClickCount())
                .likeCount(boardCreateRequest.getLikeCount())
                .professor(boardCreateRequest.getProfessor())
                .build();
    }

}
