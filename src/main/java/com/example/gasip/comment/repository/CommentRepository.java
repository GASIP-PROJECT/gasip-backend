package com.example.gasip.comment.repository;

import com.example.gasip.board.model.Board;
import com.example.gasip.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 전체 조회
    @Query("select c from Comment c where c.parentComment is NULL")
    List<Comment> findComment();

    // 부모 댓글 조회
    List<Comment> findCommentByParentComment(Comment parentComment_id);

    // 특정 게시글 댓글 조회
    @Query("select c, b from Comment c, Board b where c.parentComment is NULL and c.board.postId = b.postId")
    List<Comment> findCommentByBoard(Board postId);
}
