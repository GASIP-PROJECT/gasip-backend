package com.example.gasip.professor.dto;

import com.example.gasip.professor.model.Professor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "교수 DTO Response 관련 VO")
public class ProfessorResponse {

    @Schema(description = "교수 ID")
    private Long profId;
    @Schema(description = "교수 이름")
    private String profName;
    @Schema(description = "교수 전공 ID")
    private Long majorId;
    @Schema(description = "교수 전공 이름")
    private String majorName;

    public static ProfessorResponse fromEntity(Professor professor) {
        return ProfessorResponse.builder()
                .profId(professor.getProfId())
                .majorId(professor.getMajor().getMajorId())
                .majorName(professor.getMajor().getMajorName())
                .profName(professor.getProfName())
                .build();
    }
}
