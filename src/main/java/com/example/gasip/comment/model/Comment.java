package com.example.gasip.comment.model;

import com.example.gasip.board.model.Board;
import com.example.gasip.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Board board;
    @Column(nullable = false)
    private String writer;
    private Long commentLike;

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

}
