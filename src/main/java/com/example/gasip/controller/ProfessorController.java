package com.example.gasip.controller;

import com.example.gasip.entity.ProfessorEntity;
import com.example.gasip.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.gasip.repository.ProfessorRepository;

import java.util.List;

//@RestController // JSON 형태 결과값을 반환해줌 (@ResponseBody가 필요없음)
@Controller
//@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. (Autowired 역할)
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }


    /**
     * 교수 조회
     */
//    @GetMapping("prof")
//    public String list(Model model) {
////        List<ProfessorEntity> prof = professorService.findProfessor();
////        model.addAttribute("prof", prof);
//        return "professor/professorlist";
//    }
    @GetMapping("prof")
    public String list(Model model) {
        return "professor/professorlist";
    }


}
