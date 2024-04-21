package com.example.gasip.comment.service;

import com.example.gasip.board.model.Board;
import com.example.gasip.board.repository.BoardRepository;
import com.example.gasip.comment.dto.*;
import com.example.gasip.comment.model.Comment;
import com.example.gasip.comment.repository.CommentRepository;
import com.example.gasip.global.constant.ErrorCode;
import com.example.gasip.global.exception.BoardNotFoundException;
import com.example.gasip.global.exception.MemberNotFoundException;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.member.model.Member;
import com.example.gasip.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 댓글 create
    @Transactional
    public CommentCreateResponse createComment(MemberDetails memberDetails, CommentCreateRequest commentCreateRequest, Long boardId) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(() -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER));
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new BoardNotFoundException(ErrorCode.NOT_FOUND_BOARD));
        Comment comment = null;
        Comment parentComment = null;
        if (commentCreateRequest.getParentId() != null) {
            parentComment = commentRepository.getReferenceById(commentCreateRequest.getParentId());
        }

        if (commentCreateRequest.getParentId() != null &&
            parentComment.getCommentChildren() != null &&
            boardId.equals(parentComment.getBoard().getPostId())) {
            comment = commentRepository.save(commentCreateRequest.toEntity(board, member));
            comment.updateParent(parentComment);
            parentComment.updateCommentChildren(comment);
        } else {
            comment = commentRepository.save(commentCreateRequest.toEntity(board, member));
        }
        return CommentCreateResponse.fromEntity(comment);
    }

    // 특정 게시글 댓글 조회
    @Transactional(readOnly = true)
    public List<CommentReadResponse> findCommentByBoard(Long postId) {
        Board board = boardRepository.findById(postId).orElseThrow(() -> new BoardNotFoundException(ErrorCode.NOT_FOUND_BOARD));
        return commentRepository.findAllByBoard(board)
            .stream()
            .map(CommentReadResponse::fromEntity)
            .collect(Collectors.toList());
    }

    // 댓글 edit
    @Transactional
    public CommentUpdateResponse editComment(MemberDetails memberDetails, CommentUpdateRequest commentUpdateRequest, Long commentId) {
        Comment comment = validatedCommentWritter(memberDetails, commentId);
        comment.updateComment(commentUpdateRequest.getContent());
        return CommentUpdateResponse.fromEntity(comment);
    }

    // 댓글 delete
    public String deleteComment(MemberDetails memberDetails,Long commentId) {
        validatedCommentWritter(memberDetails, commentId);
        commentRepository.deleteById(commentId);
        return commentId + "번 댓글이 삭제되었습니다";
    }

    private Comment validatedCommentWritter(MemberDetails memberDetails, Long commentId) {
        Comment comment = validateCommentEmpty(commentId);
        Member member = memberRepository.getReferenceById(memberDetails.getId());
        if (!comment.getMember().getMemberId().equals(member.getMemberId())) {
            throw new IllegalArgumentException();
        }
        return comment;
    }

    private Comment validateCommentEmpty(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                IllegalArgumentException::new
        );
    }


}
