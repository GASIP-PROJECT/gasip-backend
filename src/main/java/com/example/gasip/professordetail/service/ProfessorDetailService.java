package com.example.gasip.professordetail.service;

import com.example.gasip.professordetail.dto.ProfessorDetailResponse;
import com.example.gasip.professordetail.model.ProfessorDetail;
import com.example.gasip.professordetail.repository.ProfessorDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfessorDetailService {
    private final ProfessorDetailRepository professorDetailRepository;
    @Transactional
    public ProfessorDetailResponse findByProfId(Long profId) {
        ProfessorDetail detailProfessor = professorDetailRepository.findById(profId).orElseThrow(IllegalArgumentException::new);
        return ProfessorDetailResponse.fromEntity(detailProfessor);
    }
}
