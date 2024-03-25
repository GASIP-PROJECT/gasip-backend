package com.example.gasip.grade.controller;

import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.grade.dto.request.GradeCreateRequest;
import com.example.gasip.grade.service.GradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Grade Controller", description = "평점 CRUD와 관련된 API입니다.")
@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;

    @PostMapping("{profId}") // 전체 게시글 작성 시 profId = 0
    @Operation(summary = "평점 생성 요청", description = "평점 생성 요청합니다.", tags = { "Grade Controller" })
    public ResponseEntity<?> createGrade(
        @AuthenticationPrincipal MemberDetails memberDetails,
        @RequestBody @Valid GradeCreateRequest gradeCreateRequest,
        @PathVariable Long profId) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                ApiUtils.success(
                    gradeService.createGrade(gradeCreateRequest, memberDetails, profId)
                )
            );
    }

    @GetMapping("{profId}")
    @Operation(summary = "교수별 평점 조회 요청", description = "교수별 평점을 조회를 요청합니다.", tags = { "Grade Controller" })
    public ResponseEntity<?> findProfessorGradepoint(@PathVariable Long profId) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    gradeService.findProfessorGradepoint(profId)
                )
            );
    }
}
