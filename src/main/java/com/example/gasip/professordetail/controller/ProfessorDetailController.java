package com.example.gasip.professordetail.controller;

import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.professordetail.service.ProfessorDetailService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
