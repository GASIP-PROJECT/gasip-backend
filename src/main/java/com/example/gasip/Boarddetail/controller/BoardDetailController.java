package com.example.gasip.Boarddetail.controller;

import com.example.gasip.Boarddetail.dto.BoardDetailResponse;
import com.example.gasip.Boarddetail.service.BoardDetailService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/details")
public class BoardDetailController {
    private final BoardDetailService boardDetailService;

    @GetMapping("/{postId}")
    @Operation(summary = "게시글 상세 정보 불러오기", description = "게시글 상세 정보를 불러옵니다.", tags = { "BoardDetail Controller" })
    public ResponseEntity<BoardDetailResponse> getBoardDetail(@PathVariable Long postId) {
        return ResponseEntity.ok(boardDetailService.findByID(postId));
    }
}
