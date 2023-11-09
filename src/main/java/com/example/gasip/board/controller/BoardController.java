package com.example.gasip.board.controller;

import com.example.gasip.board.dto.*;
import com.example.gasip.board.service.BoardService;
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
    @PostMapping("/create/{profId}")
    public ResponseEntity<BoardCreateResponse> createBoard(@RequestBody @Valid BoardCreateRequest boardCreateRequest,@PathVariable Long profId) {
        return ResponseEntity.ok(boardService.createBoard(boardCreateRequest,profId));
    }

    @GetMapping("")
    public ResponseEntity<List<BoardReadResponse>> findAllBoard() {
        return ResponseEntity.ok(boardService.findAllBoard());
    }

    @GetMapping("{postId}")
    public ResponseEntity<BoardReadResponse> findByBoardId(@PathVariable Long postId) {
        return ResponseEntity.ok(boardService.findBoardId(postId));
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<BoardUpdateResponse> editBoard(
            @PathVariable Long boardId,
            @RequestBody @Valid BoardUpdateRequest boardUpdateRequest) {
        return ResponseEntity.ok(boardService.editBoard(boardId,boardUpdateRequest));
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.deleteBoard(boardId));

    }
}
