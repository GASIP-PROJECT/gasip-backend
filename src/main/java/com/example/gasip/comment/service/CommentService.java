package com.example.gasip.comment.service;

import com.example.gasip.board.dto.BoardReadResponse;
import com.example.gasip.board.model.Board;
import com.example.gasip.board.repository.BoardRepository;
import com.example.gasip.comment.dto.CommentCreateRequest;
import com.example.gasip.comment.dto.CommentDto;
import com.example.gasip.comment.model.Comment;
import com.example.gasip.comment.repository.CommentRepository;
import com.example.gasip.exception.BoardNotFoundException;
import com.example.gasip.exception.CommentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    // 댓글 목록 전체 조회
    @Transactional(readOnly = true)
    public List<CommentDto> CommentList() {
//        List<CommentDto> commentCreateResponseList = commentRepository.findComment().stream().map(CommentDto::fromEntity).collect(Collectors.toList());
        List<Comment> comments = commentRepository.findComment();
        List<CommentDto> commentCreateResponseList = new ArrayList<>();
        for(Comment comment : comments) {
            commentCreateResponseList.add(CommentDto.ToEntity(comment));
        }
        return commentCreateResponseList;
    }

    // 특정 게시글 댓글 조회
    @Transactional(readOnly = true)
    public List<CommentDto> findCommentByBoard(Board postId) {
        List<Comment> comments = commentRepository.findCommentByBoard(postId);
        List<CommentDto> findCommentByBoard = new ArrayList<>();
        for(Comment comment : comments) {
            findCommentByBoard.add(CommentDto.ToEntity(comment));
        }
        return findCommentByBoard;
    }

    // 댓글 create
    @Transactional
    public CommentDto createComment(CommentCreateRequest commentCreateRequest) {
        Comment comment = commentRepository.save(
                Comment.createComment(
                        commentCreateRequest.getContent(),
                        boardRepository.findById(commentCreateRequest.getPostId()).orElseThrow(BoardNotFoundException::new),
                        commentCreateRequest.getWriter(),
                        commentCreateRequest.getParentId() != null ?
                                commentRepository.findById(commentCreateRequest.getParentId()).orElseThrow(CommentNotFoundException::new) : null)
        );

//                Comment.createComment(commentCreateRequest.getContent(),
//                commentCreateRequest.getParentId() != null ?
//                        commentRepository.findById(commentCreateRequest.getParentId()).orElseThrow(CommentNotFoundException::new) : null);
        return CommentDto.ToEntity(comment);
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
