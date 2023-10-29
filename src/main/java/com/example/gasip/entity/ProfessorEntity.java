package com.example.gasip.entity;

import com.example.gasip.dto.ProfessorDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Builder;
import java.util.Objects;

import java.math.BigInteger;

@Entity

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

    protected ProfessorEntity () {

    public static ProfessorEntity professorEntity(ProfessorDto professorDto) {
        ProfessorEntity professorEntity = new ProfessorEntity();
        professorEntity.setProf_ID(professorDto.getProf_Id());
        professorEntity.setMajor_ID(professorDto.getMajor_Id());
        professorEntity.setMajor_name(professorDto.getMajor_name());
        professorEntity.setProf_name(professorDto.getProf_name());

        return professorEntity;

    }

    @Builder
    private ProfessorEntity(Long professor_Id, Long major_Id, String major, String name) {
        this.professor_Id = professor_Id;
        this.major_Id = major_Id;
        this.major = major;
        this.name = name;
    }


    public static ProfessorEntity professorEntity(ProfessorDto professorDto) {
        return new ProfessorEntity(
                professorDto.getProfessor_Id(),
                professorDto.getMajor_Id(),
                professorDto.getMajor(),
                professorDto.getName()
        );

    }

    public static ProfessorEntity of(Long professor_Id, Long major_Id, String major, String name) {
        return new ProfessorEntity(professor_Id, major_Id, major, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfessorEntity that)) return false;
        return professor_Id != null && professor_Id.equals(that.getProfessor_Id());

    }

    @Override
    public int hashCode() {
        return Objects.hash(professor_Id);
    }

}
