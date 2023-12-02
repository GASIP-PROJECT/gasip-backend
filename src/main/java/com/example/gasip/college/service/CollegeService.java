package com.example.gasip.college.service;

import com.example.gasip.college.dto.CollegeResponse;
import com.example.gasip.college.model.College;
import com.example.gasip.college.repository.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CollegeService {
    private final CollegeRepository collegeRepository;

    @Transactional(readOnly = true)
    public List<CollegeResponse> findAllCollege() {
        List<College> colleges = collegeRepository.findAll();
        List<CollegeResponse> collegeResponseList = new ArrayList<>();
        for (College college : colleges) {
            collegeResponseList.add(CollegeResponse.fromEntity(college));
        }
        return collegeResponseList;
    }
}
