package com.example.gasip.Boarddetail.model;


import com.example.gasip.common.BaseTimeEntity;
import com.example.gasip.professor.model.Professor;
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
public class BoardDetail extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Column(nullable = false, length = 500)
    private String content;
    @Column(nullable = true)
    private Long clickCount;
    @Column(nullable = true)
    private Long likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prof_ID")
    private Professor professor;

    public BoardDetail(LocalDateTime regDate, LocalDateTime updateDate, Long postId, String content, Long clickCount, Long likeCount, Professor professor) {
        super(regDate, updateDate);
        this.postId = postId;
        this.content = content;
        this.clickCount = clickCount;
        this.likeCount = likeCount;
        this.professor = professor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardDetail that)) return false;
        return postId != null && postId.equals(that.getPostId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }

}
