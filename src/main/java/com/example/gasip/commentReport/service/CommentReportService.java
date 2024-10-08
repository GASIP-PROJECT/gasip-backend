package com.example.gasip.commentReport.service;

import com.example.gasip.board.model.Board;
import com.example.gasip.board.model.ContentActivity;
import com.example.gasip.board.repository.BoardRepository;
import com.example.gasip.comment.model.Comment;
import com.example.gasip.comment.repository.CommentRepository;
import com.example.gasip.commentReport.dto.CommentReportRequest;
import com.example.gasip.commentReport.dto.CommentReportResponse;
import com.example.gasip.commentReport.model.CommentReport;
import com.example.gasip.commentReport.repository.CommentReportRepository;
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
public class CommentReportService {
    private static final int REPORT_LIMIT = 1;
    private final CommentReportRepository commentReportRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentReportResponse commentReportInsert(CommentReportRequest commentReportRequest, MemberDetails memberDetails) throws Exception {

        Member member = memberRepository.getReferenceById(memberDetails.getId());

        Board board = boardRepository.getReferenceById(commentReportRequest.getPostId());

        Comment comment = commentRepository.getReferenceById(commentReportRequest.getCommentId());

        if (commentReportRepository.findByMemberAndCommentAndBoard(member, comment, board).isPresent()) {
            throw new DuplicateReportException(ErrorCode.DUPLICATE_REPORT);
        }

        if (commentReportRepository.countByComment_CommentId(commentReportRequest.getCommentId()) > REPORT_LIMIT) {
            comment.changeActivity(ContentActivity.FLAGGED);
        }

        CommentReport commentReport = CommentReport.builder()
                .member(member)
                .comment(comment)
                .board(board)
                .content(commentReportRequest.getContent())
                .build();

        commentReportRepository.save(commentReport);
        commentRepository.addReportCount(comment);

        return CommentReportResponse.fromEntity(commentReport);

    }

    @Transactional
    public CommentReportResponse commentReportDelete(CommentReportRequest commentReportRequest, MemberDetails memberDetails) {

        Member member = memberRepository.getReferenceById(memberDetails.getId());

        Board board = boardRepository.getReferenceById(commentReportRequest.getPostId());

        Comment comment = commentRepository.findById(commentReportRequest.getCommentId())
                .orElseThrow(() -> new NotFoundException(("Could not found comment id : " + commentReportRequest.getCommentId())));

        CommentReport commentReport = commentReportRepository.findByMemberAndCommentAndBoard(member, comment, board)
                .orElseThrow(() -> new NotFoundException("Could not found report id"));

        commentReportRepository.save(commentReport);
        commentRepository.subReportCount(comment);

        return CommentReportResponse.fromEntity(commentReport);
    }


    @Transactional
    public Boolean isReport(Long commentId, Long memberId) {
        return commentReportRepository.existsByComment_CommentIdAndMember_MemberId(commentId, memberId);
    }

    private void validateSelfReport(Long reporterId, Long reportedUId) {
        if (reporterId == reportedUId) {
            throw new SelfBoardReportException(ErrorCode.CANNOT_REPORT_YOURSELF);
        }
    }
}
