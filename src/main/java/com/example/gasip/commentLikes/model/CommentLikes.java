package com.example.gasip.commentLikes.model;

import com.example.gasip.board.model.Board;
import com.example.gasip.comment.model.Comment;
import com.example.gasip.member.model.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "commentLikes")
public class CommentLikes {
    @Id
    private Long likesId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Board board;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Builder
    public CommentLikes(Member member, Board board, Comment comment) {
        this.member = member;
        this.board = board;
        this.comment = comment;
    }
}
