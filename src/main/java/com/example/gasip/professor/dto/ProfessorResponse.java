package com.example.gasip.professor.dto;

import com.example.gasip.professor.model.Professor;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Schema(description = "교수 DTO Response 관련 VO")
public class ProfessorResponse {

    @Schema(description = "교수 ID")
    private Long profId;
    @Schema(description = "교수 이름")
    private String profName;
    @Schema(description = "단과대 이름")
    private String collegeName;
    @Schema(description = "교수 전공 ID")
    private Long majorId;
    @Schema(description = "교수 전공 이름")
    private String majorName;
    @Schema(description = "교수 평균 평점")
    private String professorAverageGradePoint;
    @Schema(description = "교수 평점 작성 여부")
    private Boolean isGrade;

    @QueryProjection
    public ProfessorResponse(Long profId, String profName, String collegeName, Long majorId, String majorName) {
        this.profId = profId;
        this.profName = profName;
        this.collegeName = collegeName;
        this.majorId = majorId;
        this.majorName = majorName;
    }

    public static ProfessorResponse fromEntity(Professor professor) {
        return ProfessorResponse.builder()
                .profId(professor.getProfId())
                .profName(professor.getProfName())
                .collegeName(professor.getCategory().getCollegeName())
                .majorId(professor.getCategory().getId())
                .majorName(professor.getCategory().getMajorName())
                .professorAverageGradePoint(professor.getAverageGradePoint())
                .isGrade(professor.getIsGrade())
                .build();
    }
}
