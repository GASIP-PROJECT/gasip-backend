package com.example.gasip.controller;

import com.example.gasip.entity.ProfessorEntity;
import com.example.gasip.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;
import java.util.List;

@Controller
public class ProfessorDetailController {
    private final ProfessorService professorService;

    public ProfessorDetailController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    /**
     * 교수 상세페이지 조회
     */
    @GetMapping("prof/details")
    public String profDetails(Long prof_ID, Model model) {
        model.addAttribute("profDetails", professorService.findProfDetails(prof_ID));
        return "professor/professorDetail";
    }
}
