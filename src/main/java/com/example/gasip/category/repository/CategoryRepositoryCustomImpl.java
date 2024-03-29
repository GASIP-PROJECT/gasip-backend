package com.example.gasip.category.repository;

import com.example.gasip.category.dto.CategoryResponse;
import com.querydsl.core.types.Projections;
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
                .select(Projections.constructor(CategoryResponse.class, category.Id, category.collegeName, category.majorName, category.parentCategory))
                .from(category)
                .where(category.parentCategory.isNull())
                .fetch();
    }

//    private BooleanExpression idNull() {
//        return (category.parentCategory == null) ? null : category.parentCategory.eq(category.parentCategory);
//    }

//    @Override
//    public List<CategoryDTO> findAllByParentCategory() {
//        return queryFactory
//                .select(new QCategoryDTO(category.Id, category.collegeId, category.collegeName, category.majorId, category.majorName, category.parentCategory, category.children))
//                .from(category)
//                .where(isNull(category.parentCategory))
//                .fetch();
//    }
}
