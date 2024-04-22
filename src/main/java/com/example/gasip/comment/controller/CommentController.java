package com.example.gasip.comment.controller;

import com.example.gasip.comment.dto.CommentCreateRequest;
import com.example.gasip.comment.dto.CommentUpdateRequest;
import com.example.gasip.comment.service.CommentService;
import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.global.security.MemberDetails;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    // 댓글 create
    @PostMapping("{boardId}")
    @Operation(summary = "댓글 생성 요청", description = "댓글 생성을 요청합니다.", tags = { "Comment Controller" })
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

    // 특정 게시글 댓글 read
    @GetMapping("{postId}")
    @Operation(summary = "게시글에 해당하는 댓글 조회 요청", description = "특정 게시글에 작성된 댓글을 조회합니다.", tags = { "Comment Controller" })
    public ResponseEntity<?> findCommentByBoard(@PathVariable Long postId, @AuthenticationPrincipal MemberDetails memberDetails) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    commentService.findCommentByBoard(postId, memberDetails)
                )
            );
    }

    // 특정 게시글 댓글 edit
    @PutMapping("/{commentId}")
    @Operation(summary = "댓글 수정 요청", description = "댓글 수정을 요청합니다.", tags = { "Comment Controller" })
    public ResponseEntity<?> editComment(
        @AuthenticationPrincipal MemberDetails memberDetails,
        @RequestBody @Valid CommentUpdateRequest commentUpdateRequest,
        @PathVariable Long commentId) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    commentService.editComment(memberDetails,commentUpdateRequest,commentId)
                )
            );
    }

    // 게시글 delete
    @DeleteMapping("/{commentId}")
    @Operation(summary = "댓글 삭제 요청", description = "댓글 삭제를 요청합니다.", tags = { "Comment Controller" })
    public ResponseEntity<?> deleteComment(
        @AuthenticationPrincipal MemberDetails memberDetails,
        @PathVariable Long commentId) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    commentService.deleteComment(memberDetails,commentId)
                )
            );
    }
}
