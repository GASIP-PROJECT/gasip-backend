package com.example.gasip.college.service;

import com.example.gasip.college.dto.CollegeResponse;
import com.example.gasip.college.repository.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollegeService {
    private final CollegeRepository collegeRepository;

    @Transactional(readOnly = true)
    public List<CollegeResponse> findAllCollege() {
        return collegeRepository.findAll()
            .stream()
            .map(CollegeResponse::fromEntity)
            .collect(Collectors.toList());
    }
}
