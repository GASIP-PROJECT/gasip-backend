package com.example.gasip.professordetail.controller;

import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.professordetail.service.ProfessorDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ProfessorDetail Controller", description = "교수의 상세정보를 호출하는 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/professors")
public class ProfessorDetailController {
    private final ProfessorDetailService professorDetailService;

    /**
     * 교수 상세페이지 조회
     */
    @GetMapping("/{profId}")
    @Operation(summary = "교수 상세 정보 불러오기", description = "교수 상세 정보를 불러옵니다.", tags = { "ProfessorDetail Controller" })
    @Parameter(name = "profId", description = "조회하고 싶은 교수의 profId를 URL을 통해 입력받습니다.")
    public ResponseEntity<?> getProfessorDetail(@PathVariable Long profId) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    professorDetailService.findByProfId(profId)
                )
            );
    }
}
