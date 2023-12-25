package com.example.gasip.comment.controller;

import com.example.gasip.board.model.Board;
import com.example.gasip.comment.dto.CommentCreateRequest;
import com.example.gasip.comment.service.CommentService;
import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.global.security.MemberDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/all-comments")
public class CommentController {
    private final CommentService commentService;

    // 댓글 create
    @PostMapping("{boardId}")
    public ResponseEntity<?> createComment(
        @AuthenticationPrincipal MemberDetails memberDetails,
        @RequestBody @Valid CommentCreateRequest commentCreateRequest,
        @PathVariable Long boardId) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                ApiUtils.success(
                    commentService.createComment(memberDetails,commentCreateRequest,boardId)
                )
            );
    }

    // 전체 댓글 read
    @GetMapping("")
    public ResponseEntity<?> findComment(){
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    commentService.CommentList()
                )
            );
    }

    // 특정 게시글 댓글 read
    @GetMapping("{postId}")
    public ResponseEntity<?> findCommentByBoard(@PathVariable Long postId) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    commentService.findCommentByBoard(postId)
                )
            );
    }

    // 게시글 delete
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    commentService.deleteComment(commentId)
                )
            );
    }
}
