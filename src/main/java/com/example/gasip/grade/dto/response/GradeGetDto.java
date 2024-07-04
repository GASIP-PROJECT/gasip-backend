package com.example.gasip.grade.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GradeGetDto {
    private Double averageGradepoint;

    @QueryProjection
    public GradeGetDto(Double averageGradepoint) {
        // averageGradepoint가 null이면 0으로 설정
        this.averageGradepoint = Math.round((averageGradepoint != null ? averageGradepoint : 0) * 10) / 10.0;
    }

    @Override
    public String toString() {
        return String.valueOf(averageGradepoint);
    }
}
