package com.example.gasip.major.dto;

import com.example.gasip.college.model.College;
import com.example.gasip.major.model.Major;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@NoArgsConstructor
@SuperBuilder
public class MajorResponse {

    @NotNull
    private Long majorId;
    @NotNull
    private String majorName;

    private Long collegeId;
    private String collegeName;


    public static MajorResponse toEntity(Major major) {
        return MajorResponse.builder()
                .majorId(major.getMajorId())
                .majorName(major.getMajorName())
                .collegeId(major.getCollege().getCollegeId())
                .collegeName(major.getCollege().getCollegeName())
                .build();
    }

}
