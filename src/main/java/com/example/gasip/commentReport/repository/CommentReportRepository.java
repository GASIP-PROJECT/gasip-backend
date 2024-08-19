package com.example.gasip.commentReport.repository;

import com.example.gasip.board.model.Board;
import com.example.gasip.comment.model.Comment;
import com.example.gasip.commentReport.model.CommentReport;
import com.example.gasip.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentReportRepository extends JpaRepository<CommentReport, Long> {
    Optional<CommentReport> findByMemberAndCommentAndBoard(Member member, Comment comment, Board board);

    Boolean existsByComment_CommentIdAndMember_MemberId(Long commentId, Long memberId);

    Integer countByComment_CommentId(Long commentId);
}
