package com.example.gasip.grade.service;

import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.grade.dto.request.GradeCreateRequest;
import com.example.gasip.grade.dto.response.GradeCreateResponse;
import com.example.gasip.grade.dto.response.GradeGetDto;
import com.example.gasip.grade.model.Grade;
import com.example.gasip.grade.repository.GradeRepository;
import com.example.gasip.grade.repository.GradeRepositoryCustom;
import com.example.gasip.member.model.Member;
import com.example.gasip.member.repository.MemberRepository;
import com.example.gasip.professor.model.Professor;
import com.example.gasip.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    private final ProfessorRepository professorRepository;
    private final MemberRepository memberRepository;
    public GradeCreateResponse createGrade(GradeCreateRequest gradeCreateRequest, MemberDetails memberDetails, Long profId) {
        Professor professor = professorRepository.getReferenceById(profId);
        Member member = memberRepository.getReferenceById(memberDetails.getId());
        if (gradeRepository.findByProfessorAndMember(professor, member) != null) {
            throw new IllegalArgumentException("이미 평점 등록하셨습니다.");
        }
        if (5 < gradeCreateRequest.getGradepoint() || 0 > gradeCreateRequest.getGradepoint()) {
            throw new IllegalArgumentException("평점은 0~5점 사이로 입력해주세요.");
        }
        Grade grade = gradeRepository.save(gradeCreateRequest.toEntity(professor,member));
        return GradeCreateResponse.fromEntity(grade);
    }


    public List<GradeGetDto> findProfessorGradepoint(Long profId) {
        return gradeRepository.professorAverageGradepoint(profId);
    }
}
