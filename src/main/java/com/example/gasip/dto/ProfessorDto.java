package com.example.gasip.dto;
import com.example.gasip.entity.ProfessorEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class ProfessorDto {

    private Long professor_Id;
    private Long major_Id;
    private String major;
    private String name;


    public static ProfessorDto toProfessorDto(ProfessorEntity professorEntity) {
        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setProfessor_Id(professorEntity.getProfessor_Id());
        professorDto.setMajor_Id(professorEntity.getMajor_Id());
        professorDto.setMajor(professorEntity.getMajor());
        professorDto.setName(professorEntity.getName());

        return professorDto;
    }

    /**
    빌더 패턴 사용
     ***
    public ProfessorEntity toEntity() {
        ProfessorEntity build = ProfessorEntity.builder()
                .professor_Id(professor_Id)
                .major_Id(major_Id)
                .major(major)
                .name(name)
                .build();
        return build;
    }

    @Builder
    public ProfessorDto(Long professor_Id, Long major_Id, String major, String name) {
        this.professor_Id = professor_Id;
        this.major_Id = major_Id;
        this.major = major;
        this.name = name;
    }
     **/
}
