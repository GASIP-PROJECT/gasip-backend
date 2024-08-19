package com.example.gasip.boardReport.dto;

import com.example.gasip.boardReport.model.BoardReport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class BoardReportResponseDto {
    private Long reportId;
    private Long postId;
    private Long memberId;
    private String content;

    public static BoardReportResponseDto fromEntity(BoardReport boardReport) {
        return BoardReportResponseDto.builder()
                .reportId(boardReport.getReportId())
                .postId(boardReport.getBoard().getPostId())
                .memberId(boardReport.getMember().getMemberId())
                .content(boardReport.getContent())
                .build();
    }
}
