package com.example.gasip.Board.controller;

import com.example.gasip.Board.dto.*;
import com.example.gasip.Board.model.Board;
import com.example.gasip.Board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("")
    public ResponseEntity<BoardCreateResponse> createBoard(@RequestBody @Valid BoardCreateRequest boardCreateRequest) {
        return ResponseEntity.ok(boardService.createBoard(boardCreateRequest));
    }

    @GetMapping("")
    public ResponseEntity<List<BoardReadResponse>> findAllBoard() {
        return ResponseEntity.ok(boardService.findAllBoard());
    }
//    public List<Board> findAllBoard() {
//        return boardService.findAllBoard();
//    }

    @GetMapping("{postId}")
    public ResponseEntity<BoardReadResponse> findByBoardId(@PathVariable Long postId) {
        return ResponseEntity.ok(boardService.findBoardId(postId));
    }

    @PutMapping("{boardId}")
    public ResponseEntity<BoardUpdateResponse> editBoard(
            @PathVariable Long boardId,
            @RequestBody @Valid BoardUpdateRequest boardUpdateRequest) {
        return ResponseEntity.ok(boardService.editBoard(boardId,boardUpdateRequest));
    }

    @DeleteMapping("{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.deleteBoard(boardId));

    }
}
