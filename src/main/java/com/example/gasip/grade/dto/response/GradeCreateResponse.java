package com.example.gasip.grade.dto.response;

import com.example.gasip.grade.model.Grade;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Schema(description = "평점 생성 Response DTO")
public class GradeCreateResponse {
    @Schema(description = "입력 평점")
    private int gradepoint;

    public static GradeCreateResponse fromEntity(Grade grade) {
        return GradeCreateResponse.builder()
            .gradepoint(grade.getGradepoint())
            .build();
    }
}
