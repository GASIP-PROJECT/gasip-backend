package com.example.gasip.professor.service;

import com.example.gasip.professor.dto.ProfessorResponse;
import com.example.gasip.professor.model.Professor;
import com.example.gasip.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    /**
     * 교수 조회
     */
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }


    /**
     * 특정 교수 불러오기
     */
    public ProfessorResponse findByProfId(Long profId) {
        Professor professor = professorRepository.findById(profId)
                .orElseThrow(IllegalArgumentException::new);
        return ProfessorResponse.fromEntity(professor);
    }
}
