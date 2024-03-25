package com.example.gasip.college.dto;

import com.example.gasip.college.model.College;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class CollegeResponse {

    @NotNull
    private Long collegeId;
    @NotNull
    private String collegeName;

    public static CollegeResponse fromEntity(College college) {
        return CollegeResponse.builder()
                .collegeId(college.getCollegeId())
                .collegeName(college.getCollegeName())
                .build();
    }
}
