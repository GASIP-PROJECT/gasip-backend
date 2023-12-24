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

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String writer;
    private Long commentLike;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<Comment> commentChildren = new ArrayList<>();


    public static Comment createComment(String content, Board board, String writer, Comment parentComment){
        Comment comment = new Comment();
        comment.content = content;
        comment.board = board;
        comment.writer = writer;
        comment.parentComment = parentComment;
        return comment;
    }

    public void updateParent(Comment comment) {
        this.parentComment = comment;
    }

}
