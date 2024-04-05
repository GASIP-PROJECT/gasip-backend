package com.example.gasip.grade.service;

import com.example.gasip.global.constant.ErrorCode;
import com.example.gasip.global.exception.DuplicateGradeCreateException;
import com.example.gasip.global.exception.InvalidGradePointException;
import com.example.gasip.global.exception.MemberNotFoundException;
import com.example.gasip.global.exception.ProfessorNotFoundException;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.grade.dto.request.GradeCreateRequest;
import com.example.gasip.grade.dto.request.GradeUpdateRequest;
import com.example.gasip.grade.dto.response.GradeCreateResponse;
import com.example.gasip.grade.dto.response.GradeGetDto;
import com.example.gasip.grade.dto.response.GradeUpdateResponse;
import com.example.gasip.grade.model.Grade;
import com.example.gasip.grade.repository.GradeRepository;
import com.example.gasip.member.model.Member;
import com.example.gasip.member.repository.MemberRepository;
import com.example.gasip.professor.model.Professor;
import com.example.gasip.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    private final ProfessorRepository professorRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public GradeCreateResponse createGrade(GradeCreateRequest gradeCreateRequest, MemberDetails memberDetails, Long profId) {
        Professor professor = professorRepository.getReferenceById(profId);
        Member member = memberRepository.getReferenceById(memberDetails.getId());
        if (gradeRepository.findByProfessorAndMember(professor, member) != null) {
            throw new DuplicateGradeCreateException(ErrorCode.DUPLICATE_GRADE);
        }
        if (5 < gradeCreateRequest.getGradepoint() || 0 > gradeCreateRequest.getGradepoint()) {
            throw new InvalidGradePointException(ErrorCode.INVALID_GRADEPOINT);
        }
        Grade grade = gradeRepository.save(gradeCreateRequest.toEntity(professor,member));
        return GradeCreateResponse.fromEntity(grade);
    }

    @Transactional(readOnly = true)
    public List<GradeGetDto> findProfessorGradepoint(Long profId) {
        return gradeRepository.professorAverageGradepoint(profId);
    }
    @Transactional
    public GradeUpdateResponse updateProfessorGradepoint(MemberDetails memberDetails, GradeUpdateRequest gradeUpdateRequest, Long profId) {
        Professor professor = professorRepository.findById(profId).orElseThrow(() -> new ProfessorNotFoundException(ErrorCode.NOT_FOUND_PROFESSOR));
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(() -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER));;
        if (5 < gradeUpdateRequest.getGradepoint() || 0 > gradeUpdateRequest.getGradepoint()) {
            throw new InvalidGradePointException(ErrorCode.INVALID_GRADEPOINT);
        }
        Grade grade = gradeRepository.findByProfessorAndMember(professor, member);
        grade.updateGradepoint(gradeUpdateRequest.getGradepoint());
        return GradeUpdateResponse.fromEntity(grade);
    }
}
