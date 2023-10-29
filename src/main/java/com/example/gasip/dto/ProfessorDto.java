package com.example.gasip.dto;

import com.example.gasip.entity.ProfessorEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@ToString
public class ProfessorDto {

    private Long prof_Id;
    private Long major_Id;
    private String major_name;
    private String prof_name;

    public static ProfessorDto toProfessorDto(ProfessorEntity professorEntity) {

        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setProf_Id(professorEntity.getProfId());
        professorDto.setMajor_Id(professorEntity.getMajorId());
        professorDto.setMajor_name(professorEntity.getMajorName());
        professorDto.setProf_name(professorEntity.getProfName());

        return professorDto;

    }

}
