package com.example.gasip.college.controller;

import com.example.gasip.college.service.CollegeService;
import com.example.gasip.global.api.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/all-colleges")
public class CollegeController {
    private final CollegeService collegeService;

    @GetMapping("")
    public ResponseEntity<?> findAllCollege() {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    collegeService.findAllCollege()
                )
            );
    }
}
