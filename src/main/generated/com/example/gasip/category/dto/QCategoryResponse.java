package com.example.gasip.category.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.gasip.category.dto.QCategoryResponse is a Querydsl Projection type for CategoryResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCategoryResponse extends ConstructorExpression<CategoryResponse> {

    private static final long serialVersionUID = 2093442943L;

    public QCategoryResponse(com.querydsl.core.types.Expression<Long> Id, com.querydsl.core.types.Expression<String> collegeName, com.querydsl.core.types.Expression<String> majorName, com.querydsl.core.types.Expression<? extends com.example.gasip.category.model.Category> parentCategory) {
        super(CategoryResponse.class, new Class<?>[]{long.class, String.class, String.class, com.example.gasip.category.model.Category.class}, Id, collegeName, majorName, parentCategory);
    }

}

