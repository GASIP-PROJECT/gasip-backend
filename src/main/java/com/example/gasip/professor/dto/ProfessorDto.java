package com.example.gasip.professor.dto;

import com.example.gasip.professor.model.Professor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "교수 DTO 관련 VO")
public class
ProfessorDto {

    @Schema(description = "교수 ID")
    private Long profId;
    @Schema(description = "교수 전공 ID")
    private Long majorId;
    @Schema(description = "교수 전공 이름")
    private String majorName;
    @Schema(description = "교수 이름")
    private String profName;

    public static ProfessorDto toProfessorDto(Professor professorEntity) {

        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setProfId(professorEntity.getProfId());
        professorDto.setMajorId(professorEntity.getMajor().getMajorId());
        professorDto.setMajorName(professorEntity.getMajor().getMajorName());
        professorDto.setProfName(professorEntity.getProfName());

        return professorDto;

    }

}
