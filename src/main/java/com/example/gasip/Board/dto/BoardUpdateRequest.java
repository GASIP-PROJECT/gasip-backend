package com.example.gasip.Board.dto;

import com.example.gasip.Board.model.Board;
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
    private Long profId;

    public Board toEntity(BoardUpdateRequest boardUpdateRequest) {
        return Board.builder()
                .content(boardUpdateRequest.getContent())
                .clickCount(boardUpdateRequest.getClickCount())
                .likeCount(boardUpdateRequest.getLikeCount())
                .regDate(boardUpdateRequest.getRegDate())
                .updateDate(boardUpdateRequest.getUpdateDate())
                .profId(boardUpdateRequest.getProfId())
                .build();
    }
}
