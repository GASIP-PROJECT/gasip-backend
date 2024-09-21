package com.example.gasip.commentReport.model;

import com.example.gasip.board.model.Board;
import com.example.gasip.comment.model.Comment;
import com.example.gasip.member.model.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "CommentReport")
@SuperBuilder
public class CommentReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    @Schema(description = "ID", example = "1")
    private Long reportId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Schema(description = "reporter 유저 ID (현재 로그인된 유저)", example = "1")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @Schema(description = "신고하는 게시글의 ID", example = "12")
    private Board board;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    @Schema(description = "신고 내용", example = "신고 내용을 적어주세요")
    private Comment comment;
    @Column(nullable = false,length = 500)
    private String content;
    @Column(name = "reported_at")
    @Schema(description = "신고 시간", example = "2024-09-21 13:26:36.206416")
    private LocalDateTime reportedAt;

    @PostPersist
    private void setCreatedAt() {
        reportedAt = LocalDateTime.now();
    }

    public CommentReport(Member member, Board board, Comment comment, String content) {
        this.member = member;
        this.board = board;
        this.comment = comment;
        this.content = content;
    }
}
