package com.example.gasip.comment.repository;

import com.example.gasip.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 전체 조회
    @Query("select c from Comment c where c.parentComment = null")
    List<Comment> findComment();

    // 부모 댓글 조회
    List<Comment> findCommentByParentComment(Comment parentComment_id);

    // 특정 게시글 댓글 조회
    // 찾을게 없어도 error 코드 안뜸 -> 수정 필요
    @Query("select c from Comment c where c.board.postId = :postId")
    List<Comment> findCommentByBoard(@Param("postId") Long postId);

}
