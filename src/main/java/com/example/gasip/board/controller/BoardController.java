package com.example.gasip.board.controller;

import com.example.gasip.board.dto.*;
import com.example.gasip.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @PostMapping("")
    @Operation(summary = "게시글 생성 요청", description = "게시글을 생성을 요청합니다.", tags = { "Board Controller" })
    public ResponseEntity<BoardCreateResponse> createBoard(@RequestBody @Valid BoardCreateRequest boardCreateRequest) {
        return ResponseEntity.ok(boardService.createBoard(boardCreateRequest));
    }

    @GetMapping("")
    @Operation(summary = "전체 게시글 조회 요청", description = "전체 게시글을 조회를 요청합니다.", tags = { "Board Controller" })
    public ResponseEntity<List<BoardReadResponse>> findAllBoard() {
        return ResponseEntity.ok(boardService.findAllBoard());
    }

    @GetMapping("{postId}")
    @Operation(summary = "게시글 상세 조회 요청", description = "게시글의 상세 내용을 조회를 요청합니다.", tags = { "Board Controller" })
    public ResponseEntity<BoardReadResponse> findByBoardId(@PathVariable Long postId) {
        return ResponseEntity.ok(boardService.findBoardId(postId));
    }

    @PutMapping("/{boardId}")
    @Operation(summary = "게시글 수정 요청", description = "게시글을 수정을 요청합니다.", tags = { "Board Controller" })
    public ResponseEntity<BoardUpdateResponse> editBoard(
            @PathVariable Long boardId,
            @RequestBody @Valid BoardUpdateRequest boardUpdateRequest) {
        return ResponseEntity.ok(boardService.editBoard(boardId,boardUpdateRequest));
    }

    @DeleteMapping("/{boardId}")
    @Operation(summary = "게시글 삭제 요청", description = "게시글 삭제를 요청합니다.", tags = { "Board Controller" })
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.deleteBoard(boardId));

    }
}
