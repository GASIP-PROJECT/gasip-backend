package com.example.gasip.professor.service;

import com.example.gasip.grade.dto.response.GradeGetDto;
import com.example.gasip.grade.repository.GradeRepository;
import com.example.gasip.major.model.Major;
import com.example.gasip.professor.dto.ProfessorResponse;
import com.example.gasip.professor.model.Professor;
import com.example.gasip.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final GradeRepository gradeRepository;

    /**
     * 교수 조회
     */
    @Transactional
    public List<ProfessorResponse> findAll() {
        return professorRepository.findAll()
            .stream()
            .map(ProfessorResponse::fromEntity)
            .collect(Collectors.toList());
    }


    /**
     * 특정 교수 불러오기
     */
    @Transactional
    public ProfessorResponse findByProfId(Long profId) {
        Professor professor = professorRepository.findById(profId)
                .orElseThrow(IllegalArgumentException::new);
        String gradePoint = gradeRepository.professorAverageGradepoint(profId).get(0).toString();
        professor.updateProfessor(gradePoint);
        return ProfessorResponse.fromEntity(professor);
    }

    /**
     * 특정 학과 교수 불러오기
     */
    @Transactional
    public List<ProfessorResponse> findProfByMajor(Major majorId) {
        return professorRepository.findProfessorByMajor(majorId)
                .stream()
                .map(ProfessorResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
