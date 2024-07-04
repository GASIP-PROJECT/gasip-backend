package com.example.gasip.grade.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GradeGetDto {
    private Double averageGradepoint;

    @QueryProjection
    public GradeGetDto(Double averageGradepoint) {
        this.averageGradepoint = Math.round(averageGradepoint*10)/10.0;
    }

    @Override
    public String toString() {
        return String.valueOf(averageGradepoint);
    }
}
