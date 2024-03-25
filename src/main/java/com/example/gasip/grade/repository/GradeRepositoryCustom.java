package com.example.gasip.grade.repository;

import com.example.gasip.grade.dto.response.GradeGetDto;

import java.util.List;

public interface GradeRepositoryCustom {

    List<GradeGetDto> professorAverageGradepoint(Long profId);
}
