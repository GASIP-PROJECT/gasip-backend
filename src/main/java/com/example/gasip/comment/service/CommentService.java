package com.example.gasip.comment.service;

import com.example.gasip.board.model.Board;
import com.example.gasip.board.repository.BoardRepository;
import com.example.gasip.comment.dto.CommentCreateRequest;
import com.example.gasip.comment.dto.CommentDto;
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
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 댓글 목록 전체 조회
    @Transactional(readOnly = true)
    public List<CommentDto> CommentList() {
        return commentRepository.findComment()
            .stream()
            .map(CommentDto::fromEntity)
            .collect(Collectors.toList());
    }

    // 특정 게시글 댓글 조회
    @Transactional(readOnly = true)
    public List<CommentDto> findCommentByBoard(Board postId) {
        return commentRepository.findCommentByBoard(postId)
            .stream()
            .map(CommentDto::fromEntity)
            .collect(Collectors.toList());
    }

    // 댓글 create
    @Transactional
    public CommentDto createComment(MemberDetails memberDetails, CommentCreateRequest commentCreateRequest, Long boardId) {
        Member member = memberRepository.getReferenceById(memberDetails.getId());
        Board board = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
        Comment comment = commentRepository.save(commentCreateRequest.toEntity(board,member));

        Comment parentComment;
        if (commentCreateRequest.getParentId() != null) {
            parentComment = commentRepository.findById(commentCreateRequest.getParentId()).orElseThrow(IllegalArgumentException::new);
            comment.updateParent(parentComment);
        }
//        Comment comment = commentRepository.save(
//                Comment.createComment(
//                commentCreateRequest.getContent(),
//                boardRepository.findById(commentCreateRequest.getPostId()).orElseThrow(BoardNotFoundException::new),
//                commentCreateRequest.getWriter(),
//                commentCreateRequest.getParentId() != null ?
//                    commentRepository.findById(commentCreateRequest.getParentId()).orElseThrow(CommentNotFoundException::new) : null)
//        );

        return CommentDto.fromEntity(comment);
    }

    // 댓글 delete
    public String deleteComment(Long commentId) {
        validateCommentEmpty(commentId);
        commentRepository.deleteById(commentId);
        return commentId + "번 댓글이 삭제되었습니다";
    }

    private Comment validateCommentEmpty(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                IllegalArgumentException::new
        );
    }

}
