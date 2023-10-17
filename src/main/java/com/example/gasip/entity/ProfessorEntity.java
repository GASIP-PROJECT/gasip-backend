package com.example.gasip.entity;

import com.example.gasip.dto.ProfessorDto;
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

    @Column
    private Long major_Id;

    @Column
    private String major;

    @Column
    private String name;



    public static ProfessorEntity professorEntity(ProfessorDto professorDto) {
        ProfessorEntity professorEntity = new ProfessorEntity();
        professorEntity.setProfessor_Id(professorDto.getProfessor_Id());
        professorEntity.setMajor_Id(professorDto.getMajor_Id());
        professorEntity.setMajor(professorDto.getMajor());
        professorEntity.setName(professorDto.getName());

        return professorEntity;
    }

    /**
    빌더 패턴 사용
    **
    @Builder
    public ProfessorEntity(Long professor_Id, Long major_Id, String major, String name) {
        this.professor_Id = professor_Id;
        this.major_Id = major_Id;
        this.major = major;
        this.name = name;
    }
     */
}
