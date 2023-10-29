package com.example.gasip.professor.dto;

import com.example.gasip.professor.model.Professor;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@ToString
public class ProfessorDto {

    private Long profId;
    private Long majorId;
    private String majorName;
    private String profName;

    public static ProfessorDto toProfessorDto(Professor professorEntity) {

        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setProfId(professorEntity.getProfId());
        professorDto.setMajorId(professorEntity.getMajorId());
        professorDto.setMajorName(professorEntity.getMajorName());
        professorDto.setProfName(professorEntity.getProfName());

        return professorDto;

    }

}
