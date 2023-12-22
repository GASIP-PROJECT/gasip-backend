package com.example.gasip.comment.controller;

import com.example.gasip.board.model.Board;
import com.example.gasip.comment.dto.CommentCreateRequest;
import com.example.gasip.comment.dto.CommentDto;
import com.example.gasip.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/all-comments")
public class CommentController {
    private final CommentService commentService;

    // 댓글 create
    @PostMapping("{post_id}")
    public ResponseEntity<CommentDto> createComment(@RequestBody @Valid CommentCreateRequest commentCreateRequest) {
        return ResponseEntity.ok(commentService.createComment(commentCreateRequest));
    }

    // 전체 댓글 read
    @GetMapping("")
    public ResponseEntity<?> findComment(){
        return ResponseEntity.ok(commentService.CommentList());
    }

    // 특정 게시글 댓글 read
    @GetMapping("{postId}")
    public ResponseEntity<?> findCommentByBoard(@PathVariable Board postId) {
        return ResponseEntity.ok(commentService.findCommentByBoard(postId));
    }

    // 게시글 delete
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }
}
