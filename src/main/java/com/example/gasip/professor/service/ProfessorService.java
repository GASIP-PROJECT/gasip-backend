package com.example.gasip.professor.service;

import com.example.gasip.category.model.Category;
import com.example.gasip.global.constant.ErrorCode;
import com.example.gasip.global.exception.MemberNotFoundException;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.grade.repository.GradeRepository;
import com.example.gasip.member.model.Member;
import com.example.gasip.member.repository.MemberRepository;
import com.example.gasip.professor.dto.ProfessorResponse;
import com.example.gasip.professor.dto.ProfessorWithBoardResponse;
import com.example.gasip.professor.model.Professor;
import com.example.gasip.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final GradeRepository gradeRepository;
    private final MemberRepository memberRepository;

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
    public ProfessorResponse findByProfId(Long profId,MemberDetails memberDetails) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(
            () -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER)
        );
        Professor professor = professorRepository.findById(profId)
                .orElseThrow(IllegalArgumentException::new);
        String gradePoint = gradeRepository.professorAverageGradepoint(profId).get(0).toString();
        professor.updateProfessor(gradePoint);
        if (gradeRepository.findAllByProfessorAndMember(professor, member).isEmpty()) {
            professor.updateGrade(false);
        } else {
            professor.updateGrade(true);
        }
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
    public List<ProfessorResponse> findProfessorByProfNameLike(String professorName, MemberDetails memberDetails) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(
            () -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER)
        );
        List<Professor> professors = professorRepository.findProfessorByProfNameLike(professorName);
        return professors.stream()
            .map(professor -> {
                String averageGradePoint = gradeRepository.professorAverageGradepoint(professor.getProfId()).get(0).toString();
                professor.updateProfessor(averageGradePoint);
                if (Objects.equals(professorName, "전체")) {
                    return null;
                } else {
                    if (gradeRepository.findAllByProfessorAndMember(professor, member).isEmpty()) {
                        professor.updateGrade(false);
                    } else {
                        professor.updateGrade(true);
                    }

                    return ProfessorResponse.fromEntity(professor);
                }
//                if (gradeRepository.findAllByProfessorAndMember(professor, member).isEmpty()) {
//                    professor.updateGrade(false);
//                } else {
//                    professor.updateGrade(true);
//                }
//
//                return ProfessorResponse.fromEntity(professor);
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

    /**
     * 학과로 교수 검색
     */
    @Transactional
    public List<ProfessorResponse> findProfessorByCategoryNameContaining(String majorName) {
        return professorRepository.findProfessorByCategoryNameContaining(majorName);
    }
}
