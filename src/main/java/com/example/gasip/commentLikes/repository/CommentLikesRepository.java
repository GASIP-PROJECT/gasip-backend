package com.example.gasip.commentLikes.repository;

import com.example.gasip.board.model.Board;
import com.example.gasip.comment.model.Comment;
import com.example.gasip.comment.repository.CommentRepositoryCustom;
import com.example.gasip.commentLikes.model.CommentLikes;
import com.example.gasip.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long>, CommentRepositoryCustom {
    Optional<CommentLikes> findByMemberAndCommentAndBoard(Member member, Comment comment, Board board);

    Boolean existsByComment_CommentIdAndMember_MemberId(Long commentId, Long memberId);

}
