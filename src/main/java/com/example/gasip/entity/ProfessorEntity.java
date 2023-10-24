package com.example.gasip.entity;

import com.example.gasip.dto.ProfessorDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Setter
@Getter
@Table(name = "prof")
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prof_ID;

    @Column
    private Long major_ID;

    @Column
    private String major_name;

    @Column
    private String prof_name;



    public static ProfessorEntity professorEntity(ProfessorDto professorDto) {
        ProfessorEntity professorEntity = new ProfessorEntity();
        professorEntity.setProf_ID(professorDto.getProf_Id());
        professorEntity.setMajor_ID(professorDto.getMajor_Id());
        professorEntity.setMajor_name(professorDto.getMajor_name());
        professorEntity.setProf_name(professorDto.getProf_name());

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
