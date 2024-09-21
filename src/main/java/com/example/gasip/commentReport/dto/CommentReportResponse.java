package com.example.gasip.commentReport.dto;

import com.example.gasip.commentReport.model.CommentReport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class CommentReportResponse {
    private Long reportId;
    private Long memberId;
    private Long postId;
    private Long commentId;
    private String content;

    public static CommentReportResponse fromEntity(CommentReport commentReport) {
        return CommentReportResponse.builder()
                .reportId(commentReport.getReportId())
                .memberId(commentReport.getMember().getMemberId())
                .postId(commentReport.getBoard().getPostId())
                .commentId(commentReport.getComment().getCommentId())
                .content(commentReport.getContent())
                .build();
    }
}
