package com.example.gasip.comment.model;

import com.example.gasip.board.model.Board;
import com.example.gasip.global.entity.BaseTimeEntity;
import com.example.gasip.member.model.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@DynamicInsert
@SQLDelete(sql = "UPDATE comment SET deleted = true WHERE comment_id = ?")
@Where(clause = "deleted = false")
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
    @Column(nullable = false)
    private String content;

    @ColumnDefault("0")
    private Long commentLike;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.REMOVE)
    private List<Comment> commentChildren = new ArrayList<>();

    @Transient
    private Boolean isCommentLike;

    public void updateParent(Comment comment) {
        this.parentComment = comment;
    }

    public void updateCommentChildren(Comment comment) {
        commentChildren.add(comment);
    }

    public void updateComment(String content) {
        this.content = content;
        this.updateDate = LocalDateTime.now();
    }

    public void updateCommentLike(Boolean isCommentLike) {
        this.isCommentLike=isCommentLike;
    }

}
