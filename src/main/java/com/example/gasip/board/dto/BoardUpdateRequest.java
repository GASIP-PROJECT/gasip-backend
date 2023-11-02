package com.example.gasip.board.dto;

import com.example.gasip.board.model.Board;
import com.example.gasip.professor.model.Professor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateRequest {
    @NotNull
    private String content;
    private Long clickCount;
    private Long likeCount;
    @NotNull
    private LocalDateTime regDate;
    @NotNull
    private LocalDateTime updateDate;
    @NotNull
    private Professor professor;

    public Board toEntity(BoardUpdateRequest boardUpdateRequest) {
        return Board.builder()
                .content(boardUpdateRequest.getContent())
                .clickCount(boardUpdateRequest.getClickCount())
                .likeCount(boardUpdateRequest.getLikeCount())
                .regDate(boardUpdateRequest.getRegDate())
                .updateDate(boardUpdateRequest.getUpdateDate())
                .professor(boardUpdateRequest.getProfessor())
                .build();
    }
}
