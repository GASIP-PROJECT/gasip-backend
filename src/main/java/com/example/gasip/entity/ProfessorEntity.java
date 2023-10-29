package com.example.gasip.entity;

import com.example.gasip.dto.ProfessorDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "prof")
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profId;

    @Column
    private Long majorId;

    @Column
    private String majorName;

    @Column
    private String profName;
    protected ProfessorEntity() {}

    @Builder
    public ProfessorEntity(Long profId, Long majorId, String majorName, String profName) {
            this.profId = profId;
            this.majorId = majorId;
            this.majorName = majorName;
            this.profName = profName;
        }

        public static ProfessorEntity professorEntity (ProfessorDto professorDto){
            return new ProfessorEntity(
                    professorDto.getProf_Id(),
                    professorDto.getMajor_Id(),
                    professorDto.getMajor_name(),
                    professorDto.getProf_name()
            );

        }

        @Override
        public boolean equals (Object o){
            if (this == o) return true;
            if (!(o instanceof ProfessorEntity that)) return false;
            return profId != null && profId.equals(that.profId);

        }

        @Override
        public int hashCode () {
            return Objects.hash(profId);
        }

    }



