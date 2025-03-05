package com.example.gasip.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@NoArgsConstructor
@SuperBuilder
public class BoardResponseWithPagination {
    private List<BoardReadResponse> boardReadResponses;
    private boolean hasNext;

    public BoardResponseWithPagination(List<BoardReadResponse> boardReadResponses, boolean hasNext) {
        this.boardReadResponses = boardReadResponses;
        this.hasNext = hasNext;
    }

    public static BoardResponseWithPagination fromEntity(List<BoardReadResponse> boardReadResponses, boolean hasNext) {
        return BoardResponseWithPagination.builder()
                .boardReadResponses(boardReadResponses)
                .hasNext(hasNext)
                .build();
    }
}
