package com.example.gasip.commentReport.dto;

import com.example.gasip.commentReport.model.CommentReport;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class CommentReportRequestDto {
    private Long postId;
    private Long commentId;
    private String content;

    @QueryProjection
    public CommentReportRequestDto(Long postId, Long commentId, String content) {
        this.postId = postId;
        this.commentId = commentId;
        this.content = content;
    }

    public static CommentReportRequestDto toEntity(CommentReport commentReport) {
        return CommentReportRequestDto.builder()
                .postId(commentReport.getBoard().getPostId())
                .commentId(commentReport.getComment().getCommentId())
                .content(commentReport.getContent())
                .build();
    }
}
