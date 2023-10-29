package com.example.gasip.dto;

import com.example.gasip.entity.ProfessorDetail;
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
                .majorId(detail.getMajorId())
                .majorName(detail.getMajorName())
                .profName(detail.getProfName())
                .build();
    }
}
