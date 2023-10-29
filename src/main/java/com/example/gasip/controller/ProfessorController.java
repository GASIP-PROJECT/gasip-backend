package com.example.gasip.controller;

import com.example.gasip.dto.ProfessorResponse;
import com.example.gasip.entity.Professor;
import com.example.gasip.service.ProfessorService;
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
    public ResponseEntity<List<Professor>> findAllProfessor() {
        return ResponseEntity.ok(professorService.findAll());
    }

    @GetMapping("{profId}")
    public ResponseEntity<ProfessorResponse> findByProfId(@PathVariable Long profId) {
        return ResponseEntity.ok(professorService.findByProfId(profId));
    }

}
