package com.example.gasip.professor.controller;

import com.example.gasip.category.model.Category;
import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.professor.dto.ProfessorResponse;
import com.example.gasip.professor.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("professors")
public class ProfessorController {

    private final ProfessorService professorService;

    /**
     * 교수 조회
     */
    @GetMapping("")
    @Operation(summary = "교수 전체 목록 불러오기", description = "교수 전체 목록을 불러옵니다.", tags = { "Professor Controller" })
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
    @Operation(summary = "교수 상세 정보 불러오기", description = "교수 상세 정보를 불러옵니다.", tags = { "Professor Controller" })
    public ResponseEntity<?> findByProfId(@PathVariable Long profId) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    professorService.findByProfId(profId)
                )
            );
    }


    /**
     * 특정 학과 교수
     */
    @GetMapping("/majors/{majorId}")
    @Operation(summary = "학과별 교수 정보 불러오기", description = "학과별 교수 정보를 불러옵니다.", tags = { "Professor Controller" })
    public ResponseEntity<?> findAllById(@PathVariable Category majorId) {
        return ResponseEntity
                .ok()
                .body(
                        ApiUtils.success(
                                professorService.findProfByMajor(majorId)
                        )
                );
    }

}
