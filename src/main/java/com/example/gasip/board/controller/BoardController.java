package com.example.gasip.board.controller;

import com.example.gasip.board.dto.BoardCreateRequest;
import com.example.gasip.board.dto.BoardUpdateRequest;
import com.example.gasip.board.service.BoardService;
import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.global.security.MemberDetails;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @PostMapping("{profId}")
    @Operation(summary = "게시글 생성 요청", description = "게시글을 생성을 요청합니다.", tags = { "Board Controller" })
    public ResponseEntity<?> createBoard(
        @AuthenticationPrincipal MemberDetails memberDetails,
        @RequestBody @Valid BoardCreateRequest boardCreateRequest,
        @PathVariable Long profId) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                ApiUtils.success(
                    boardService.createBoard(boardCreateRequest, memberDetails, profId)
                )
            );
    }

    @GetMapping("")
    @Operation(summary = "전체 게시글 조회 요청", description = "전체 게시글을 조회를 요청합니다.", tags = { "Board Controller" })
    public ResponseEntity<?> findAllBoard(Pageable pageable) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    boardService.findAllBoard(pageable)
                )
            );
    }

    @GetMapping("/details/{postId}")
    @Operation(summary = "교수별 게시글 상세 정보 불러오기", description = "교수별 게시글 상세 정보를 불러옵니다.", tags = { "Board Controller" })
    public ResponseEntity<?> getBoardDetail(@PathVariable Long postId) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(boardService.findBoardId(postId))
            );
    }

    @PutMapping("/{boardId}")
    @Operation(summary = "게시글 수정 요청", description = "게시글을 수정을 요청합니다.", tags = { "Board Controller" })
    public ResponseEntity<?> editBoard(
            @AuthenticationPrincipal MemberDetails memberDetails,
            @PathVariable Long boardId,
            @RequestBody @Valid BoardUpdateRequest boardUpdateRequest) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    boardService.editBoard(memberDetails,boardId,boardUpdateRequest)
                )
            );
    }

    @DeleteMapping("/{boardId}")
    @Operation(summary = "게시글 삭제 요청", description = "게시글 삭제를 요청합니다.", tags = { "Board Controller" })
    public ResponseEntity<?> deleteBoard(
        @AuthenticationPrincipal MemberDetails memberDetails,
        @PathVariable Long boardId) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    boardService.deleteBoard(memberDetails,boardId)
                )
            );
    }

    @GetMapping("/best/{profId}")
    public ResponseEntity<?> getBestBoard(@PathVariable Long profId,
    Pageable pageable) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(boardService.findBestBoard(profId,pageable))
            );
    }
}
