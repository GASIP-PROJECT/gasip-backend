package com.example.gasip.professor.repository;

import com.example.gasip.professor.dto.ProfessorResponse;
import com.example.gasip.professor.dto.ProfessorWithBoardResponse;

import java.util.List;

public interface ProfessorRepositoryCustom {
    List<ProfessorWithBoardResponse> findBoardByProfessor(Long profId);

    // 학과/학부 검색
    List<ProfessorResponse> findProfessorByCategoryNameContaining(String majorName);
    // 교수 검색
    List<ProfessorResponse> findProfessorByProfessorNameLike(String majorName);

    List<ProfessorResponse> findProfessorByProfessorNameContaining(String majorName);
}

