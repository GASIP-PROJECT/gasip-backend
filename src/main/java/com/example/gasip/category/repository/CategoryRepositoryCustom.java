package com.example.gasip.category.repository;


import com.example.gasip.category.dto.CategoryResponse;

import java.util.List;

public interface CategoryRepositoryCustom {
    List<CategoryResponse> findAllByParentCategory();
}
