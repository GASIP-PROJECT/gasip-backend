package com.example.gasip.dto;

import com.example.gasip.entity.ProfessorEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@ToString
public class ProfessorDto {

    private Long professor_Id;
    private Long major_Id;
    private String major;
    private String name;

    public static ProfessorDto toProfessorDto(ProfessorEntity professorEntity) {
    }

}
