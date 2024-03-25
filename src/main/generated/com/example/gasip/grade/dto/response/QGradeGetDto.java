package com.example.gasip.grade.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.gasip.grade.dto.response.QGradeGetDto is a Querydsl Projection type for GradeGetDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGradeGetDto extends ConstructorExpression<GradeGetDto> {

    private static final long serialVersionUID = -156049096L;

    public QGradeGetDto(com.querydsl.core.types.Expression<Double> averageGradepoint) {
        super(GradeGetDto.class, new Class<?>[]{double.class}, averageGradepoint);
    }

}

