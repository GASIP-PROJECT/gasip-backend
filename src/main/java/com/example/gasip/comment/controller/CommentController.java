package com.example.gasip.comment.controller;

import com.example.gasip.comment.dto.CommentCreateRequest;
import com.example.gasip.comment.dto.CommentUpdateRequest;
import com.example.gasip.comment.service.CommentService;
import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.global.security.MemberDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comment Controller", description = "댓글 CRUD 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    // 댓글 create
    @PostMapping("{boardId}")
    @Operation(summary = "댓글 생성 요청", description = "댓글 생성을 요청합니다.", tags = { "Comment Controller" })
    @Parameter(name = "boardId", description = "댓글을 작성 할 게시글의 boardId를 URL을 통해 입력받습니다.")
    @Parameter(name = "content", description = "댓글 내용을 입력받습니다.")
    @Parameter(name = "parentId", description = "댓글의 parentId를 입력받습니다.")
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
    @Operation(summary = "전체 댓글 조회 요청", description = "전체 댓글 리스트를 조회합니다.", tags = { "Comment Controller" })
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
    @Operation(summary = "게시글에 해당하는 댓글 조회 요청", description = "특정 게시글에 작성된 댓글을 조회합니다.", tags = { "Comment Controller" })
    @Parameter(name = "postId", description = "댓글을 조회 할 게시글의 postId를 URL을 통해 입력받습니다.")
    public ResponseEntity<?> findCommentByBoard(@PathVariable Long postId) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    commentService.findCommentByBoard(postId)
                )
            );
    }

    // 특정 게시글 댓글 edit
    @PutMapping("/{commentId}")
    @Operation(summary = "댓글 수정 요청", description = "댓글 수정을 요청합니다.", tags = { "Comment Controller" })
    @Parameter(name = "commentId", description = "수정 할 댓글의 commentId를 URL을 통해 입력받습니다.")
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
    @Parameter(name = "commentId", description = "삭제 할 댓글의 commentId를 URL을 통해 입력받습니다.")
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
