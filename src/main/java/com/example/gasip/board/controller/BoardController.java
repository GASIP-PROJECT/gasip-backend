package com.example.gasip.board.controller;

import com.example.gasip.board.dto.BoardCreateRequest;
import com.example.gasip.board.dto.BoardUpdateRequest;
import com.example.gasip.board.service.BoardService;
import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.global.security.MemberDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Board Controller", description = "게시글 CRUD와 관련된 API입니다.")
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("{profId}")
    @Operation(summary = "게시글 생성 요청", description = "특정 교수의 profId를 받아 게시글을 생성을 요청합니다.", tags = { "Board Controller" })
    @Parameter(name = "content", description = "게시글에 들어갈 내용")
    public ResponseEntity<?> createBoard(
        @AuthenticationPrincipal MemberDetails memberDetails,
        @RequestBody @Valid BoardCreateRequest boardCreateRequest,
        @Parameter(name = "profId", description = "작성할 게시글의 profId를 입력받아 해당 게시글을 create 합니다.", in = ParameterIn.PATH)
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
    @Operation(summary = "전체 게시글 조회 요청", description = "작성된 전체 게시글을 조회합니다.", tags = { "Board Controller" })
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
    @Operation(summary = "게시글 상세 정보 요청", description = "교수의 게시글 중 특정 게시글 상세 정보를 불러옵니다.", tags = { "Board Controller" })
    @Parameter(name = "profId", description = "profId를 URL을 통해 입력받아 해당 교수에 대한 특정 게시글을 조회합니다.")
    public ResponseEntity<?> getBoardDetail(@Parameter(name = "postId", description = "조회할 postId를 입력받아 해당 게시글을 조회합니다.", in = ParameterIn.PATH) @PathVariable Long postId) throws Exception {
        boardService.insertView(postId);
        return ResponseEntity
                .ok()
                .body(
                        ApiUtils.success(boardService.findBoardId(postId))
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
    @Operation(summary = "게시글 수정 요청", description = "작성된 게시글을 수정을 요청합니다.", tags = { "Board Controller" })
    @Parameter(name = "content", description = "작성된 게시글의 내용을 수정 할 content를 입력받아 수정합니다.")
    public ResponseEntity<?> editBoard(
            @AuthenticationPrincipal MemberDetails memberDetails,
            @Parameter(name = "boardId", description = "삭제할 boardId를 입력받아 해당 게시글을 수정합니다.", in = ParameterIn.PATH)
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
        @Parameter(name = "boardId", description = "삭제할 boardId를 입력받아 해당 게시글을 삭제합니다.", in = ParameterIn.PATH)
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
