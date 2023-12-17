package com.example.gasip.college.dto;

import com.example.gasip.college.model.College;
import com.example.gasip.global.entity.BaseTimeEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@SuperBuilder
public class CollegeResponse extends BaseTimeEntity {

    @NotNull
    private Long collegeId;
    @NotNull
    private String collegeName;

    public CollegeResponse(Long collegeId, LocalDateTime regDate, LocalDateTime updateDate, String collegeName) {
        super(regDate, updateDate);
        this.collegeId = collegeId;
        this.collegeName = collegeName;
    }

    public static CollegeResponse fromEntity(College college) {
        return CollegeResponse.builder()
                .collegeId(college.getCollegeId())
                .collegeName(college.getCollegeName())
                .build();
    }
}
