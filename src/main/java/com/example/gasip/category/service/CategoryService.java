package com.example.gasip.category.service;


import com.example.gasip.category.dto.CategoryDto;
import com.example.gasip.category.dto.CategoryResponse;
import com.example.gasip.category.model.Category;
import com.example.gasip.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    /**
     * college 목록 불러오기
     *
     */
    @Transactional
    public List<CategoryResponse> findAllByParentCategory() {
        return categoryRepository.findAllByParentCategory();
    }

    @Transactional
    public List<CategoryDto> findCategory() {
        return categoryRepository.findCategory()
            .stream()
            .map(CategoryDto::toEntity)
            .collect(Collectors.toList());
    }

    @Transactional
    public List<CategoryDto> findCategoryByParentCategory(Category parentCategory_id) {
        return categoryRepository.findCategoryByParentCategory(parentCategory_id)
            .stream()
            .map(CategoryDto::toEntity)
            .collect(Collectors.toList());
    }

    @Transactional
    public List<CategoryDto> findCategoryByMajorId(Long Id) {
        return categoryRepository.findCategoryById(Id)
                .stream()
                .map(CategoryDto::toEntity)
                .collect(Collectors.toList());
    }
}
