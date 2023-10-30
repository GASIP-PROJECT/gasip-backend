package com.example.gasip.Board.dto;

import com.example.gasip.Board.model.Board;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class BoardCreateRequest {

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

    public Board toEntity(BoardCreateRequest boardCreateRequest) {
        return Board.builder()
                .content(boardCreateRequest.getContent())
                .clickCount(boardCreateRequest.getClickCount())
                .likeCount(boardCreateRequest.getLikeCount())
                .regDate(boardCreateRequest.getRegDate())
                .updateDate(boardCreateRequest.getUpdateDate())
                .profId(boardCreateRequest.getProfId())
                .build();
    }

}
