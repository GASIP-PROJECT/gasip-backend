package com.example.gasip.service;

import com.example.gasip.domain.prof.ProfessorEntity;
import com.example.gasip.domain.prof.ProfessorRepository;
import com.example.gasip.dto.ProfessorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    /**
     * 교수 조회
     */
    public List<ProfessorEntity> findAll() {
        return professorRepository.findAll();
    }

    //@Transactional
//    public List<ProfessorEntity> findProfessor() {
//        return professorRepository.findAll();
//    }
//    public List<ProfessorDto> getProflist() {
//        List<ProfessorEntity> professorEntities = professorRepository.findAll();
//        List<ProfessorDto> professorDtoList = new ArrayList<>();
//
//        for(ProfessorEntity professorEntity : professorEntities) {
//            ProfessorDto professorDto = ProfessorDto.builder()
//                    .professor_Id(professorEntity.getProfessor_Id())
//                    .major_Id(professorEntity.getMajor_Id())
//                    .major(professorEntity.getMajor())
//                    .name(professorEntity.getName())
//                    .build();
//            professorDtoList.add(professorDto);
//        }
//        return professorDtoList;
//    }
}
