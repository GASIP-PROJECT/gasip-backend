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

import java.util.ArrayList;
import java.util.List;
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
                if (gradeRepository.findAllByProfessorAndMember(professor, member).isEmpty()) {
                    professor.updateGrade(false);
                } else {
                    professor.updateGrade(true);
                }
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

    /**
     * 학과/학부 키워드로 교수 검색
     */
    @Transactional
    public List<ProfessorResponse> findProfessorByCategoryNameContaining(String majorName, MemberDetails memberDetails) {
        long beforeTime = System.currentTimeMillis();
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(
            () -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER)
        );
        List<ProfessorResponse> professorResponses = new ArrayList<>();
        List<ProfessorResponse> professorResponseList = professorRepository.findProfessorByCategoryNameContaining(majorName);
        for (ProfessorResponse professorResponse : professorResponseList) {
            Professor professor = professorRepository.getReferenceById(professorResponse.getProfId());
            String averageGradePoint = gradeRepository.professorAverageGradepoint(professor.getProfId()).get(0).toString();
            professor.updateProfessor(averageGradePoint);
            if (gradeRepository.findAllByProfessorAndMember(professor, member).isEmpty()) {
                professor.updateGrade(false);
            } else {
                professor.updateGrade(true);
            }
            professorResponses.add(ProfessorResponse.fromEntity(professor));
        }
        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);
        return professorResponses;
    }

    /**
     * 교수이름 키워드를 통한 교수 검색
     */
    @Transactional
    public List<ProfessorResponse> findProfessorByProfessorNameLike(String professorName, MemberDetails memberDetails) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(
                () -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER)
        );
        List<ProfessorResponse> professorResponses = new ArrayList<>();
        List<ProfessorResponse> professorResponseList = professorRepository.findProfessorByProfessorNameLike(professorName);
        for (ProfessorResponse professorResponse : professorResponseList) {
            Professor professor = professorRepository.getReferenceById(professorResponse.getProfId());
            String averageGradePoint = gradeRepository.professorAverageGradepoint(professor.getProfId()).get(0).toString();
            professor.updateProfessor(averageGradePoint);
            if (gradeRepository.findAllByProfessorAndMember(professor, member).isEmpty()) {
                professor.updateGrade(false);
            } else {
                professor.updateGrade(true);
            }
            professorResponses.add(ProfessorResponse.fromEntity(professor));
        }
        return professorResponses;
    }
}
