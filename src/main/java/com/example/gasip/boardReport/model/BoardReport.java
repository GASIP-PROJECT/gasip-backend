package com.example.gasip.boardReport.model;

import com.example.gasip.board.model.Board;
import com.example.gasip.member.model.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "BoardReport")
@SuperBuilder
public class BoardReport{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Board board;
    @Column(nullable = false,length = 500)
    private String content;
    @Column(name = "reported_at")
    private LocalDateTime reportedAt;

    @PostPersist
    private void setCreatedAt() {
        reportedAt = LocalDateTime.now();
    }

    public BoardReport(Member member, Board board, String content) {
        this.member = member;
        this.board = board;
        this.content = content;
    }

}
