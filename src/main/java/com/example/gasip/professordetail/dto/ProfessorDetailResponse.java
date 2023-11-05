package com.example.gasip.professordetail.dto;

import com.example.gasip.professordetail.model.ProfessorDetail;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfessorDetailResponse {
    private Long profId;
    private String profName;
    private Long majorId;
    private String majorName;

    public static ProfessorDetailResponse fromEntity(ProfessorDetail detail) {
        return ProfessorDetailResponse.builder()
                .profId(detail.getProfId())
                .majorId(detail.getMajor().getMajorId())
                .majorName(detail.getMajor().getMajorName())
                .profName(detail.getProfName())
                .build();
    }
}
