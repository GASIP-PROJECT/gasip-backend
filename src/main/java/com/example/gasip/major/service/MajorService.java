package com.example.gasip.major.service;

import com.example.gasip.college.model.College;
import com.example.gasip.major.dto.MajorResponse;
import com.example.gasip.major.model.Major;
import com.example.gasip.major.repository.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MajorService {
    private final MajorRepository majorRepository;

//    /**
//     * 전공 조회
//     **/
//    @Transactional
//    public MajorResponse findByMajorId(Long majorId) {
//        Major major = majorRepository.findById(majorId).orElseThrow(IllegalArgumentException::new);
//        return MajorResponse.fromEntity(major);
//    }




}
