package com.example.gasip.major.controller;

import com.example.gasip.major.dto.MajorResponse;
import com.example.gasip.major.model.Major;
import com.example.gasip.major.service.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
