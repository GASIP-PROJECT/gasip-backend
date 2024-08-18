package com.example.gasip.boardReport.service;

import com.example.gasip.board.model.Board;
import com.example.gasip.board.model.ContentActivity;
import com.example.gasip.board.repository.BoardRepository;
import com.example.gasip.boardReport.dto.BoardReportRequestDto;
import com.example.gasip.boardReport.model.BoardReport;
import com.example.gasip.boardReport.repository.BoardReportRepository;
import com.example.gasip.global.constant.ErrorCode;
import com.example.gasip.global.exception.SelfBoardReportException;
import com.example.gasip.global.exception.handler.DuplicateReportException;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.member.model.Member;
import com.example.gasip.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class BoardReportService {
    private static final int REPORT_LIMIT = 1;
    private final BoardReportRepository boardReportRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void insert(BoardReportRequestDto boardReportRequestDto, MemberDetails memberDetails) throws Exception {
        Member member = memberRepository.getReferenceById(memberDetails.getId());

        Board board = boardRepository.findById(boardReportRequestDto.getPostId())
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + boardReportRequestDto.getPostId()));

        validateSelfReport(memberDetails.getId(), board.getMember().getMemberId());

        if (boardReportRepository.findByMemberAndBoard(member, board).isPresent()) {
            throw new DuplicateReportException(ErrorCode.DUPLICATE_REPORT);
        }

        if (boardReportRepository.countByBoard_PostId(boardReportRequestDto.getPostId()) > REPORT_LIMIT) {
            board.changeActivity(ContentActivity.FLAGGED);
        }

        BoardReport boardReport = BoardReport.builder()
                .board(board)
                .member(member)
                .content(boardReportRequestDto.getContent())
                .build();

        boardReportRepository.save(boardReport);
        boardRepository.addReportCount(board);
    }

    @Transactional
    public void delete(BoardReportRequestDto boardReportRequestDto, MemberDetails memberDetails) {

        Member member = memberRepository.getReferenceById(memberDetails.getId());

        Board board = boardRepository.findById(boardReportRequestDto.getPostId())
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + boardReportRequestDto.getPostId()));

        BoardReport boardReport = boardReportRepository.findByMemberAndBoard(member, board)
                .orElseThrow(() -> new NotFoundException("Could not found report id"));

        boardReportRepository.delete(boardReport);
        boardRepository.subReportCount(board);
    }

    @Transactional
    public Boolean isReport(Long postId, Long memberId) {
        return boardReportRepository.existsByBoard_PostIdAndMember_MemberId(postId, memberId);
    }

    private void validateSelfReport(Long reporterId, Long reportedUId) {
        if (reporterId == reportedUId) {
            throw new SelfBoardReportException(ErrorCode.CANNOT_REPORT_YOURSELF);
        }
    }
}
