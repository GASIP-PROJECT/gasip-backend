package com.example.gasip.professor.service;

import com.example.gasip.professor.domain.Professor;
import com.example.gasip.professor.dto.ProfessorResponse;
import com.example.gasip.professor.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }


    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public ProfessorResponse findByProfId(Long profId) {
        Professor professor = professorRepository.findById(profId)
                .orElseThrow(IllegalArgumentException::new);
        return ProfessorResponse.fromEntity(professor);
    }
}
