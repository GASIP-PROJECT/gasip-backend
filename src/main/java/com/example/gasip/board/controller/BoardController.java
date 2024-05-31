package com.example.gasip.board.controller;

import com.example.gasip.board.dto.BoardCreateRequest;
import com.example.gasip.board.dto.BoardUpdateRequest;
import com.example.gasip.board.service.BoardService;
import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.likes.service.LikeService;
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
    private final LikeService likeService;

    @GetMapping("/all-boards")
    @Operation(summary = "전체 게시글 정보 요청", description = "모든 게시글 목록을 최신순으로 불러옵니다.", tags = {"Board Controller"})
    public ResponseEntity<?> findAllByOrderByRegDateDesc(
        Pageable pageable,
        @AuthenticationPrincipal MemberDetails memberDetails) {
        return ResponseEntity
                .ok()
                .body(
                        ApiUtils.success(boardService.findAllByOrderByRegDateDesc(pageable, memberDetails))
                );
    }

    @GetMapping("/free-boards")
    @Operation(summary = "자유 게시글 정보 요청", description = "자유 게시글 목록을 최신순으로 불러옵니다.", tags = {"Board Controller"})
    public ResponseEntity<?> findFreeBoardByProfessor(
            Pageable pageable,
            @AuthenticationPrincipal MemberDetails memberDetails) {
        return ResponseEntity
                .ok()
                .body(
                        ApiUtils.success(boardService.findFreeBoardByProfessor(pageable, memberDetails))
                );
    }


    @PostMapping("/{profId}") // 전체 게시판 작성 시 profId = 0
    @Operation(summary = "게시글 생성 요청", description = "게시글을 생성을 요청합니다.", tags = { "Board Controller" })
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
    @GetMapping("/{profId}")
    @Operation(summary = "교수 페이지별 게시글 정보 요청", description = "교수의 전체 게시글 불러옵니다.", tags = { "Board Controller" })
    @Parameter(name = "profId", description = "profId를 URL을 통해 입력받아 해당 교수에 대한 특정 게시글을 조회합니다.")
    public ResponseEntity<?> getBoardListByProfessor(
        @AuthenticationPrincipal MemberDetails memberDetails,
        @Parameter(name = "profId", description = "조회할 profId(Professor Table PK)를 입력받아 교수 페이지 내 전체 게시글을 조회합니다.", in = ParameterIn.PATH)
        @PathVariable Long profId,
        Pageable pageable) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(boardService.findBoardByProfessor(memberDetails,profId,pageable))
            );
    }


    @GetMapping("/details/{postId}")
    @Operation(summary = "게시글 상세 정보 요청", description = "교수의 게시글 중 특정 게시글 상세 정보를 불러옵니다.", tags = { "Board Controller" })
    @Parameter(name = "postId", description = "postId를 URL을 통해 입력받아 특정 게시글을 조회합니다.")
    public ResponseEntity<?> getBoardListByProfessor(
        @Parameter(name = "postId", description = "조회할 postId를 입력받아 해당 게시글을 조회합니다.", in = ParameterIn.PATH)
        @PathVariable Long postId,
        @AuthenticationPrincipal MemberDetails memberDetails) {
        likeService.isLikes(postId, memberDetails.getId());
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(boardService.findBoardById(postId,memberDetails))

            );
    }

    @PutMapping("/{postId}")
    @Operation(summary = "게시글 수정 요청", description = "작성된 게시글을 수정을 요청합니다.", tags = { "Board Controller" })
    @Parameter(name = "content", description = "작성된 게시글의 내용을 수정 할 content를 입력받아 수정합니다.")
    public ResponseEntity<?> editBoard(
        @AuthenticationPrincipal MemberDetails memberDetails,
        @Parameter(name = "boardId", description = "수정할 boardId를 입력받아 해당 게시글을 수정합니다.", in = ParameterIn.PATH)
        @PathVariable Long postId,
        @RequestBody @Valid BoardUpdateRequest boardUpdateRequest) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    boardService.editBoard(memberDetails, postId,boardUpdateRequest)
                )
            );

    }

    @DeleteMapping("/{boardId}")
    @Operation(summary = "게시글 삭제 요청", description = "게시글 삭제를 요청합니다.", tags = { "Board Controller" })
    @Parameter(name = "boardId", description = "삭제할 boardId를 입력받아 해당 게시글을 삭제합니다.")
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

    @GetMapping("/best")
    @Operation(summary = "인기글 조회 요청", description = "인기글 조회 요청합니다.", tags = { "Board Controller" })
    public ResponseEntity<?> getBestBoard(@AuthenticationPrincipal MemberDetails memberDetails,Pageable pageable) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(boardService.findBestBoard(memberDetails,pageable))
            );
    }

    /**
     * 게시글 검색
     */
    @GetMapping("/search")
    public ResponseEntity<?> findByContentContainingOrderByRegDateDesc(String content, @AuthenticationPrincipal MemberDetails memberDetails , Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(
                        ApiUtils.success(
                                boardService.findContainingContentOrderByRegDateDesc(content, memberDetails , pageable)
                        )
                );
    }

    /**
     * 교수 게시글 검색
     */
    @GetMapping("/professor-search")
    public ResponseEntity<?> findByProfNameLike(String profName, @AuthenticationPrincipal MemberDetails memberDetails) {
        return ResponseEntity
                .ok()
                .body(
                        ApiUtils.success(
                                boardService.findByProfNameLike(profName, memberDetails)
                        )
                );
    }

    /**
     * 교수 정보 및 게시글 불러오기
     */
    //TODO Boards/{profId}와 중복 여부 확인
    @GetMapping("/boards-detail/{profId}")
    public ResponseEntity<?> findçiBoarByProfessor(@PathVariable Long profId, Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(
                        ApiUtils.success(boardService.findBoarByProfessor(profId, pageable))
                );
    }
}
