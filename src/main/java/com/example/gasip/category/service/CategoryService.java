package com.example.gasip.category.service;

import com.example.gasip.category.dto.CategoryDTO;
import com.example.gasip.category.model.Category;
import com.example.gasip.category.repository.CategoryRepository;
import com.example.gasip.major.dto.MajorResponse;
import com.example.gasip.major.repository.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public List<CategoryDTO> findCategory() {
        List<CategoryDTO> results = categoryRepository.findCategory().stream().map(CategoryDTO::toEntity).collect(Collectors.toList());
        return results;
    }

    @Transactional
    public List<CategoryDTO> findCategoryByParentCategory(Category parentCategory_id) {
        List<CategoryDTO> categoryDTOList = categoryRepository.findCategoryByParentCategory(parentCategory_id).stream().map(CategoryDTO::toEntity).collect(Collectors.toList());
        return categoryDTOList;
    }
}
