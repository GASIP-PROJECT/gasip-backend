package com.example.gasip.board.dto;

import com.example.gasip.board.model.Board;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateRequest {
    @NotNull(message = "본문은 빈 내용일 수 없습니다.")
    private String content;

    public Board toEntity(BoardUpdateRequest boardUpdateRequest) {
        return Board.builder()
                .content(boardUpdateRequest.getContent())
                .build();
    }
}
