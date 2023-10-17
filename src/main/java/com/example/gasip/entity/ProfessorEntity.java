package com.example.gasip.entity;

import com.example.gasip.dto.ProfessorDto;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "prof")
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long professor_Id;

    @Column(nullable = false)
    private Long major_Id;

    @Column(nullable = false, length = 100)
    private String major;

    @Column(nullable = false, length = 100)
    private String name;

    public static ProfessorEntity professorEntity(ProfessorDto professorDto) {
        ProfessorEntity professorEntity = new ProfessorEntity();
        professorEntity.setProfessor_Id(professorDto.getProfessor_Id());
        professorEntity.setMajor_Id(professorDto.getMajor_Id());
        professorEntity.setMajor(professorDto.getMajor());
        professorEntity.setName(professorDto.getName());

        return professorEntity;
    }
}
