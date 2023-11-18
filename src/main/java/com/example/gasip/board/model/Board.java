package com.example.gasip.board.model;

import com.example.gasip.common.BaseTimeEntity;
import com.example.gasip.professor.model.Professor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
@SuperBuilder
@Schema(description = "게시글 관련된 VO")
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "게시글 ID")
    private Long postId;
    @Column(nullable = false,length = 500)
    @Schema(description = "게시글 내용")
    private String content;
    @Column(nullable = true)
    @Schema(description = "게시글 조회수")
    private Long clickCount;
    @Column(nullable = true)
    @Schema(description = "게시글 좋아요")
    private Long likeCount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Prof_ID")
    @Schema(description = "게시글과 관련된 교수정보")
    private Professor professor;

    public Board(LocalDateTime regDate, LocalDateTime updateDate, Long postId, String content, Long clickCount, Long likeCount, Professor professor) {
        super(regDate, updateDate);
        this.postId = postId;
        this.content = content;
        this.clickCount = clickCount;
        this.likeCount = likeCount;
        this.professor = professor;
    }
    public void updateBoard(String content) {
        this.content = content;
        this.updateDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board that)) return false;
        return postId != null && postId.equals(that.getPostId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }
}