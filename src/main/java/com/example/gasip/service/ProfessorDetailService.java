package com.example.gasip.service;

import com.example.gasip.dto.ProfessorDetailResponse;
import com.example.gasip.entity.ProfessorDetail;
import com.example.gasip.repository.ProfessorDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessorDetailService {
    private final ProfessorDetailRepository professorDetailRepository;
    public ProfessorDetailResponse findByProfId(Long profId) {
        ProfessorDetail detailProfessor = professorDetailRepository.findById(profId).orElseThrow(IllegalArgumentException::new);
        return ProfessorDetailResponse.fromEntity(detailProfessor);
    }
}
