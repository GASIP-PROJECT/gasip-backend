package com.example.gasip.professor.service;

import com.example.gasip.board.repository.BoardRepository;
import com.example.gasip.category.model.Category;
import com.example.gasip.grade.repository.GradeRepository;
import com.example.gasip.professor.dto.ProfessorResponse;
import com.example.gasip.professor.dto.ProfessorWithBoardResponse;
import com.example.gasip.professor.model.Professor;
import com.example.gasip.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final GradeRepository gradeRepository;
    private final BoardRepository boardRepository;

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
    public List<ProfessorResponse> findProfByMajor(Category Id) {
        return professorRepository.findProfessorByCategory(Id)
                .stream()
                .map(ProfessorResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * 교수 이름으로 교수 상세페이지 조회
     */
    @Transactional
    public List<ProfessorResponse> findProfessorByProfNameLike(String professorName) {
        List<Professor> professors = professorRepository.findProfessorByProfNameLike(professorName);
        return professors.stream()
            .map(professor -> {
                String averageGradePoint = gradeRepository.professorAverageGradepoint(professor.getProfId()).get(0).toString();
                professor.updateProfessor(averageGradePoint);
                return ProfessorResponse.fromEntity(professor);
            })
            .collect(Collectors.toList());
    }

    /**
     * 교수 상세정보 및 교수 게시글 불러오기
     */
    @Transactional
    public List<ProfessorWithBoardResponse> findBoardByProfessor(Long profId) {
        return professorRepository.findBoardByProfessor(profId);
    }

}
