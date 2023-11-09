package com.example.gasip.board.dto;

import com.example.gasip.board.model.Board;
import com.example.gasip.common.BaseTimeEntity;
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
    private Long clickCount = 0L;
    private Long likeCount = 0L;

    public Board toEntity() {
        return Board.builder()
                .content(content)
                .clickCount(clickCount)
                .likeCount(likeCount)
                .build();
    }

}
