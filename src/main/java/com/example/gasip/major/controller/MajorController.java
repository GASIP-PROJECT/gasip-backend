package com.example.gasip.major.controller;

import com.example.gasip.major.service.MajorService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping("/all-colleges")
public class MajorController {
    private final MajorService majorService;

    /**
     * 전공 ID로 전공 목록 조회
    **/
//    @GetMapping("/major/{majorId}")
//    public ResponseEntity<MajorResponse> getMajor(@PathVariable Long majorId) {
//        return ResponseEntity.ok(majorService.findByMajorId(majorId));
//    }

}
