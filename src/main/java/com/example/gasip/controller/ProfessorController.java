package com.example.gasip.controller;

import com.example.gasip.domain.prof.ProfessorEntity;
import com.example.gasip.dto.ProfessorDto;
import com.example.gasip.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.gasip.domain.prof.ProfessorRepository;

import java.util.List;

//@RestController // JSON 형태 결과값을 반환해줌 (@ResponseBody가 필요없음)
@Controller
//@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. (Autowired 역할)
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    /**
     * 교수 조회
     */
    @GetMapping("prof")
    public String list(Model model) {
        List<ProfessorEntity> prof = professorService.findAll();
        model.addAttribute("prof", prof);
        return "professor/professorlist";
    }

    //    public String list(Model model) {
//        List<ProfessorDto> professorDtoList = ProfessorService.findAll();
//        model.addAttribute("profList", professorDtoList);
//        return "professor/professorlist";
//    }

//    @Autowired
//    public ProfessorController(ProfessorService professorService) {
//        this.professorService = professorService;
//    }

//    @GetMapping("prof")
//    public String list(Model model) {
//        return "professor/professorlist";
//    }


}
