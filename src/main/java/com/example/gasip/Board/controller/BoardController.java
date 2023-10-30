package com.example.gasip.Board.controller;

import com.example.gasip.Board.dto.BoardCreateRequest;
import com.example.gasip.Board.dto.BoardCreateResponse;
import com.example.gasip.Board.dto.BoardUpdateRequest;
import com.example.gasip.Board.dto.BoardUpdateResponse;
import com.example.gasip.Board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<BoardCreateResponse> findAllBoard() {
        return ResponseEntity.ok(boardService.findAllBoard());
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
