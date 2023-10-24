package com.example.gasip.dto;
import com.example.gasip.entity.ProfessorEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class ProfessorDto {

    private Long prof_Id;
    private Long major_Id;
    private String major_name;
    private String prof_name;


    public static ProfessorDto toProfessorDto(ProfessorEntity professorEntity) {
        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setProf_Id(professorEntity.getProf_ID());
        professorDto.setMajor_Id(professorEntity.getMajor_ID());
        professorDto.setMajor_name(professorEntity.getMajor_name());
        professorDto.setProf_name(professorEntity.getProf_name());

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
