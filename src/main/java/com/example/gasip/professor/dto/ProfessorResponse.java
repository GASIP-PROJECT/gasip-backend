package com.example.gasip.professor.dto;

import com.example.gasip.professor.model.Professor;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

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
    @Schema(description = "직위/직책")
    private String position;
    @Schema(description = "학위전공")
    private String degreeMajor;
    @Schema(description = "전화번호")
    private String tel;
    @Schema(description = "사무실 위치")
    private String location;
    @Schema(description = "학력")
    private List<String> education;

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
                .position(professor.getPosition())
                .degreeMajor(professor.getDegreeMajor())
                .tel(professor.getTel())
                .location(professor.getLocation())
                .education(professor.getEducation())
                .professorAverageGradePoint(professor.getAverageGradePoint())
                .isGrade(professor.getIsGrade())
                .build();
    }
}
