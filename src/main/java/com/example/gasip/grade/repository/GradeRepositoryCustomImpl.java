package com.example.gasip.grade.repository;

import com.example.gasip.grade.dto.response.GradeGetDto;
import com.example.gasip.grade.dto.response.QGradeGetDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.gasip.grade.model.QGrade.grade;

@RequiredArgsConstructor
public class GradeRepositoryCustomImpl implements GradeRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    @Override
    public List<GradeGetDto> professorAverageGradepoint(Long profId) {
        return queryFactory
            .select(new QGradeGetDto(grade.gradepoint.avg()))
            .from(grade)
            .where(grade.professor.profId.eq(profId))
            .fetch();
    }
}
