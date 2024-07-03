package com.example.gasip.category.repository;

import com.example.gasip.category.dto.CategoryResponse;
import com.example.gasip.category.dto.QCategoryResponse;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.gasip.category.model.QCategory.category;

@RequiredArgsConstructor
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CategoryResponse> findAllByParentCategory() {
        NumberExpression<Integer> ascii = new CaseBuilder()
                .when(category.collegeName.in("[가-힣]")).then(1)
                .otherwise(2);

        return queryFactory
                .select(new QCategoryResponse(
                        category.Id, category.collegeName, category.majorName))
                .from(category)
                .where(category.parentCategory.isNull().and(category.Id.ne(0L)))
                .orderBy(ascii.asc())
                .orderBy(category.collegeName.asc())
                .fetch();
    }
}
