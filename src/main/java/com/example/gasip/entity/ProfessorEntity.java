package com.example.gasip.entity;

import com.example.gasip.dto.ProfessorDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Builder;
import java.util.Objects;

@Entity

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

    protected ProfessorEntity () {

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
