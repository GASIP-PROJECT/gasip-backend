package com.example.gasip.commentReport.service;

import com.example.gasip.board.model.Board;
import com.example.gasip.board.model.ContentActivity;
import com.example.gasip.board.repository.BoardRepository;
import com.example.gasip.comment.model.Comment;
import com.example.gasip.comment.repository.CommentRepository;
import com.example.gasip.commentReport.dto.CommentReportRequestDto;
import com.example.gasip.commentReport.model.CommentReport;
import com.example.gasip.commentReport.repository.CommentReportRepository;
import com.example.gasip.global.constant.ErrorCode;
import com.example.gasip.global.exception.DuplicateResourceException;
import com.example.gasip.global.exception.SelfBoardReportException;
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
    public void commentReportInsert(CommentReportRequestDto commentReportRequestDto, MemberDetails memberDetails) throws Exception {

        Member member = memberRepository.getReferenceById(memberDetails.getId());

        Board board = boardRepository.getReferenceById(commentReportRequestDto.getPostId());

        Comment comment = commentRepository.getReferenceById(commentReportRequestDto.getCommentId());

        if (commentReportRepository.findByMemberAndCommentAndBoard(member, comment, board).isPresent()) {
            throw new DuplicateResourceException(ErrorCode.DUPLICATE_LIKE);
        }

        if (commentReportRepository.countByComment_CommentId(commentReportRequestDto.getCommentId()) > REPORT_LIMIT) {
            comment.changeActivity(ContentActivity.FLAGGED);
        }

        CommentReport commentReport = CommentReport.builder()
                .member(member)
                .comment(comment)
                .board(board)
                .content(commentReportRequestDto.getContent())
                .build();

        commentReportRepository.save(commentReport);
        commentRepository.addReportCount(comment);

    }

    @Transactional
    public void commentReportDelete(CommentReportRequestDto commentReportRequestDto, MemberDetails memberDetails) {

        Member member = memberRepository.getReferenceById(memberDetails.getId());

        Board board = boardRepository.getReferenceById(commentReportRequestDto.getPostId());

        Comment comment = commentRepository.findById(commentReportRequestDto.getCommentId())
                .orElseThrow(() -> new NotFoundException(("Could not found comment id : " + commentReportRequestDto.getCommentId())));

        CommentReport commentReport = commentReportRepository.findByMemberAndCommentAndBoard(member, comment, board)
                .orElseThrow(() -> new NotFoundException("Could not found report id"));

        commentReportRepository.save(commentReport);
        commentRepository.subReportCount(comment);
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
