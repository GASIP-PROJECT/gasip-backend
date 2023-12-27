package com.example.gasip.professordetail.dto;

import com.example.gasip.professordetail.model.ProfessorDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "교수 DTO 관련 VO")
public class ProfessorDetailResponse {
    @Schema(description = "교수 ID")
    private Long profId;
    @Schema(description = "교수 이름")
    private String profName;
    @Schema(description = "교수 전공 ID")
    private Long majorId;
    @Schema(description = "교수 전공 이름")
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
