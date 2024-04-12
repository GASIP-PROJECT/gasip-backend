package com.example.gasip.category.repository;

import com.example.gasip.category.dto.CategoryResponse;
import com.example.gasip.category.dto.QCategoryResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.gasip.category.model.QCategory.category;

@RequiredArgsConstructor
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CategoryResponse> findAllByParentCategory() {
        return queryFactory
                .select(new QCategoryResponse(
                        category.Id, category.collegeName, category.majorName, category.parentCategory))
                .from(category)
                .where(category.parentCategory.isNull())
                .fetch();
    }
}
