package com.example.gasip.board.model;

import com.example.gasip.professor.model.Professor;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Column(nullable = false,length = 500)
    private String content;
    @Column(nullable = true)
    private Long clickCount;
    @Column(nullable = true)
    private Long likeCount;
    @Column(nullable = false)
    private LocalDateTime regDate;
    @Column(nullable = false)
    private LocalDateTime updateDate;
    @ManyToOne
    @JoinColumn(name = "Prof_ID")
    private Professor professor;

    @Builder
    public Board(Long postId, String content, Long clickCount, Long likeCount, LocalDateTime regDate, LocalDateTime updateDate, Professor professor) {
        this.postId = postId;
        this.content = content;
        this.clickCount = clickCount;
        this.likeCount = likeCount;
        this.regDate = regDate;
        this.updateDate = updateDate;
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