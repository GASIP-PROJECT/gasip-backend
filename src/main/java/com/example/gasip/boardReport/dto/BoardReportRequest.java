package com.example.gasip.boardReport.dto;

import com.example.gasip.boardReport.model.BoardReport;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class BoardReportRequest {
    private Long postId;
    private String content;

    @QueryProjection
    public BoardReportRequest(Long postId, String content) {
        this.postId = postId;
        this.content = content;
    }

    public static BoardReportRequest toEntity(BoardReport boardReport) {
        return BoardReportRequest.builder()
                .postId(boardReport.getBoard().getPostId())
                .content(boardReport.getContent())
                .build();
    }
}
