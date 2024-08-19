package com.example.gasip.boardReport.repository;

import com.example.gasip.board.model.Board;
import com.example.gasip.boardReport.model.BoardReport;
import com.example.gasip.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardReportRepository extends JpaRepository<BoardReport, Long> {
    Optional<BoardReport> findByMemberAndBoard(Member member, Board board);

    Boolean existsByBoard_PostIdAndMember_MemberId(Long boardId, Long memberId);

    Integer countByBoard_PostId(Long boardId);
}
