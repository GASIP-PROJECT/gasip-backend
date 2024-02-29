package com.example.gasip.comment.service;

import com.example.gasip.profboard.model.ProfBoard;
import com.example.gasip.profboard.repository.ProfBoardRepository;
import com.example.gasip.comment.dto.*;
import com.example.gasip.comment.model.Comment;
import com.example.gasip.comment.repository.CommentRepository;
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
    private final ProfBoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 댓글 create
    @Transactional
    public CommentCreateResponse createComment(MemberDetails memberDetails, CommentCreateRequest commentCreateRequest, Long boardId) {
        Member member = memberRepository.getReferenceById(memberDetails.getId());
        ProfBoard profBoard = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
        Comment comment = commentRepository.save(commentCreateRequest.toEntity(profBoard,member));

        Comment parentComment;
        if (commentCreateRequest.getParentId() != null) {
            parentComment = commentRepository.findById(commentCreateRequest.getParentId()).orElseThrow(IllegalArgumentException::new);
            comment.updateParent(parentComment);
            parentComment.updateCommentChildren(comment);
        }
        return CommentCreateResponse.fromEntity(comment);
    }

    // 댓글 목록 전체 조회
    @Transactional(readOnly = true)
    public List<CommentReadResponse> CommentList() {
        return commentRepository.findComment()
            .stream()
            .map(CommentReadResponse::fromEntity)
            .collect(Collectors.toList());
    }

    // 특정 게시글 댓글 조회
    @Transactional(readOnly = true)
    public List<CommentReadResponse> findCommentByBoard(Long postId) {
        ProfBoard profBoard = boardRepository.getReferenceById(postId);
        List<CommentReadResponse> commentlist = commentRepository.findAllByBoard(profBoard)
            .stream()
            .map(CommentReadResponse::fromEntity)
            .collect(Collectors.toList());
        if (commentlist.isEmpty()) {
            throw new IllegalArgumentException("적합한 댓글이 없습니다.");
        } else {
            return commentlist;
        }
    }

    // 댓글 edit
    @Transactional
    public CommentUpdateResponse editComment(MemberDetails memberDetails, CommentUpdateRequest commentUpdateRequest,  Long commentId) {
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
