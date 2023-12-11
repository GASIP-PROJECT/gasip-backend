package com.example.gasip.college.controller;

import com.example.gasip.college.dto.CollegeResponse;
import com.example.gasip.college.service.CollegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/all-colleges")
public class CollegeController {
    private final CollegeService collegeService;

    @GetMapping("")
    public ResponseEntity<List<CollegeResponse>> findAllCollege() {
        return ResponseEntity.ok(collegeService.findAllCollege());
    }
}
