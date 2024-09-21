package com.example.gasip.commentReport.dto;

import com.example.gasip.commentReport.model.CommentReport;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class CommentReportRequest {
    private Long postId;
    private Long commentId;
    private String content;

    @QueryProjection
    public CommentReportRequest(Long postId, Long commentId, String content) {
        this.postId = postId;
        this.commentId = commentId;
        this.content = content;
    }

    public static CommentReportRequest toEntity(CommentReport commentReport) {
        return CommentReportRequest.builder()
                .postId(commentReport.getBoard().getPostId())
                .commentId(commentReport.getComment().getCommentId())
                .content(commentReport.getContent())
                .build();
    }
}
