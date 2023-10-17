package com.example.gasip.service;

import com.example.gasip.dto.ProfessorDto;
import com.example.gasip.entity.ProfessorEntity;
import com.example.gasip.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

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

    // Dto
//    public List<ProfessorDto> findAll() {
//        return professorRepository.findAll();
//    }

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
