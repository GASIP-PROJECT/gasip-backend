package com.example.gasip.board.dto;

import com.example.gasip.board.model.Board;
import com.example.gasip.board.model.ContentActivity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 수정 Request DTO 관련 VO")
public class BoardActivityRequestDto {
    private ContentActivity contentActivity;

    public Board toEntity(BoardActivityRequestDto boardActivityRequestDto) {
        return Board.builder()
                .contentActivity(boardActivityRequestDto.getContentActivity())
                .build();
    }
}
