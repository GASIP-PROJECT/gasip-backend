package com.example.gasip.board.controller;

import com.example.gasip.board.dto.BoardCreateRequest;
import com.example.gasip.board.dto.BoardReadRequest;
import com.example.gasip.board.dto.BoardReadResponse;
import com.example.gasip.board.dto.BoardUpdateRequest;
import com.example.gasip.board.service.BoardService;
import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.global.entity.HttpResponseEntity;
import com.example.gasip.global.security.MemberDetails;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.example.gasip.global.entity.HttpResponseEntity.success;

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
    public ResponseEntity<?> findAllBoard() {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    boardService.findAllBoard()
                )
            );
    }

    @GetMapping("/details/{postId}")
    @Operation(summary = "교수별 게시글 상세 정보 불러오기", description = "교수별 게시글 상세 정보를 불러옵니다.", tags = { "Board Controller" })
    public ResponseEntity<?> getBoardDetail(@RequestBody @Valid BoardReadRequest boardReadRequest) throws Exception {
        boardService.insertView(boardReadRequest);
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(boardService.findBoardId(boardReadRequest.getPostId()))
            );
    }

//    @GetMapping("/details/{postId}")
//    @Operation(summary = "게시글 상세 조회 요청", description = "게시글의 상세 내용을 조회를 요청합니다.", tags = { "Board Controller" })
//    public ResponseEntity<?> findByBoardId(@PathVariable Long postId) {
//        return ResponseEntity
//            .ok()
//            .body(
//                ApiUtils.success(
//                    boardService.findBoardId(postId)
//                )
//            );
//    }

//    @GetMapping("{postId}")
//    public HttpResponseEntity.ResponseResult<?> insertView(@RequestBody @Valid BoardReadRequest boardReadRequest) throws Exception {
//        boardService.insertView(boardReadRequest);
//        return success();
//            }


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
}
