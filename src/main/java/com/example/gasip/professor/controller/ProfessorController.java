package com.example.gasip.professor.controller;

import com.example.gasip.category.model.Category;
import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.professor.dto.ProfessorCrawlRequest;
import com.example.gasip.professor.dto.ProfessorResponse;
import com.example.gasip.professor.service.ProfessorDataCrawlingService;
import com.example.gasip.professor.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("professors")
public class ProfessorController {

    private final ProfessorService professorService;
    private final ProfessorDataCrawlingService professorDataCrawlingService;

    /**
     * 교수 조회
     */
    @GetMapping("")
    @Operation(summary = "교수 전체 목록 불러오기", description = "교수 전체 목록을 불러옵니다.", tags = {"Professor Controller"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = ProfessorResponse.class))),
    })
    public ResponseEntity<?> findAllProfessor() {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    professorService.findAll()
                )
            );
    }


    /**
     * 특정 교수 정보 조회
     */
    @GetMapping("{profId}")
    @Operation(summary = "교수 상세 정보 불러오기", description = "교수 상세 정보를 불러옵니다.", tags = {"Professor Controller"})
    public ResponseEntity<?> findByProfId(@PathVariable Long profId, Pageable pageable,
                                          @AuthenticationPrincipal MemberDetails memberDetails) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    professorService.findByProfId(profId, memberDetails)

                )
            );
    }


    /**
     * 특정 학과 교수
     */
    @GetMapping("/majors/{Id}")
    @Operation(summary = "학과별 교수 정보 불러오기", description = "학과별 교수 정보를 불러옵니다.", tags = {"Professor Controller"})
    public ResponseEntity<?> findAllById(@PathVariable Category Id) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    professorService.findProfByMajor(Id)
                )
            );
    }

    /**
     * 교수 이름으로 검색
     */
    @GetMapping("/search")
    @Operation(summary = "교수 이름으로 교수 목록을 조회합니다.(사용하지 않는 API 입니다.)", description = "교수 이름으로 검색된 교수 목록을 불러옵니다.\n 현재는 사용하지 않는 API 입니다.", tags = {"Professor Controller"})
    public ResponseEntity<?> findProfessorByProfNameLike(String profName,
                                                         @AuthenticationPrincipal MemberDetails memberDetails) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    professorService.findProfessorByProfNameLike(profName, memberDetails)
                )
            );
    }

    /**
     * 교수 정보 및 게시글 불러오기 로직
     */
    @GetMapping("/boards-detail/{profId}")
    public ResponseEntity<?> findBoardByProfessor(@PathVariable Long profId) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(professorService.findBoardByProfessor(profId))
            );
    }

    /**
     * 학과로 교수 검색
     */
    @GetMapping("/major-search")
    @Operation(summary = "학과/학부 이름으로 교수 목록을 조회합니다.", description = "학과/학부 이름으로 검색된 교수 목록을 불러옵니다.", tags = {"Professor Controller"})
    public ResponseEntity<?> findProfessorByCategoryNameContaining(String majorName,
                                                                   @AuthenticationPrincipal MemberDetails memberDetails) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    professorService.findProfessorByCategoryNameContaining(majorName, memberDetails)
                )
            );
    }

    /**
     * 학과/학부 키워드를 통한 교수 검색
     */
    @GetMapping("/prof-search")
    @Operation(summary = "교수 이름으로 교수 목록을 조회합니다.", description = "교수 이름으로 검색된 교수 목록을 불러옵니다.", tags = {"Professor Controller"})
    public ResponseEntity<?> findProfessorByProfessorNameLike(String profName,
                                                              @AuthenticationPrincipal MemberDetails memberDetails) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    professorService.findProfessorByProfessorNameLike(profName, memberDetails)
                )
            );
    }

    @PostMapping("/crawl/info")
    public ResponseEntity<?> CrawlingProfessorInfo(@RequestBody ProfessorCrawlRequest professorCrawlRequest,
                                                   @AuthenticationPrincipal MemberDetails memberDetails) throws IOException {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    professorDataCrawlingService.CrawlingProfessorInfo(professorCrawlRequest)
                )
            );
    }
}
