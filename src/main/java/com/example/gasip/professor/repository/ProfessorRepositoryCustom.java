package com.example.gasip.professor.repository;

import com.example.gasip.professor.dto.ProfessorWithBoardResponse;

import java.util.List;

public interface ProfessorRepositoryCustom {
    List<ProfessorWithBoardResponse> findBoardByProfessor(Long profId);
}

