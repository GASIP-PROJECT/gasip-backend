package com.example.gasip.professor.controller;

import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.major.model.Major;
import com.example.gasip.professor.dto.ProfessorResponse;
import com.example.gasip.professor.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Professor Controller", description = "Professor 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/all-professors")
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
    @Parameter(name = "profId", description = "조회하고 싶은 교수의 profId를 URL을 통해 입력받습니다.")
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
    @GetMapping("/major/{majorId}")
    @Operation(summary = "학과별 교수 정보 불러오기", description = "특정 학과의 모든 교수 정보를 불러옵니다.", tags = { "Professor Controller" })
    @Parameter(name = "majorId", description = "조회하고 싶은 특정 학과의 majorId를 URL을 통해 입력받습니다.")
    public ResponseEntity<?> findAllById(@PathVariable Major majorId) {
        return ResponseEntity
                .ok()
                .body(
                        ApiUtils.success(
                                professorService.findProfByMajor(majorId)
                        )
                );
    }

}
