package com.example.gasip.professor.controller;

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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/all-professors")
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping("")
    @Operation(summary = "교수 전체 목록 불러오기", description = "교수 전체 목록을 불러옵니다.", tags = { "Professor Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ProfessorResponse.class))),
//            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
//            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
//            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    public ResponseEntity<List<ProfessorResponse>> findAllProfessor() {
        return ResponseEntity.ok(professorService.findAll());
    }

    @GetMapping("{profId}")
    @Operation(summary = "교수 상세 정보 불러오기", description = "교수 상세 정보를 불러옵니다.", tags = { "Professor Controller" })
    public ResponseEntity<ProfessorResponse> findByProfId(@PathVariable Long profId) {
        return ResponseEntity.ok(professorService.findByProfId(profId));
    }

}
