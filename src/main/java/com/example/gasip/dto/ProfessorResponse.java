package com.example.gasip.dto;

import com.example.gasip.entity.Professor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfessorResponse {

    private Long profId;
    private String profName;
    private Long majorId;
    private String majorName;

    public static ProfessorResponse fromEntity(Professor professor) {
        return ProfessorResponse.builder()
                .profId(professor.getProfId())
                .majorId(professor.getMajorId())
                .majorName(professor.getMajorName())
                .profName(professor.getProfName())
                .build();
    }
}