package com.example.gasip.category.controller;

import com.example.gasip.category.model.Category;
import com.example.gasip.category.service.CategoryService;
import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.major.model.Major;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Category Controller", description = "학과 정보와 관련된 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("all-colleges")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    @Operation(summary = "전체 학과 Get 요청", description = "전체 학과 목록을 요청합니다.", tags = { "Category Controller" })
    public ResponseEntity<?> findCategory() {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    categoryService.findCategory()
                )
            );
    }

    @GetMapping("/categories/{parentCategory_id}")
    @Operation(summary = "단과대 Get 요청", description = "전체 단과대 목록을 요청합니다.", tags = { "Category Controller" })
    @Parameter(name = "parentCategory_id", description = "조회 할 단과대를 parentCategory_id 통해 요청")
    public ResponseEntity<?> findCategoryByParentCategory(@PathVariable Category parentCategory_id) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    categoryService.findCategoryByParentCategory(parentCategory_id)
                )
            );
    }

    @GetMapping("/categories/{parentCategory_id}/{major_id}")
    @Operation(summary = "학과 Get 요청", description = "단과대에 소속된 학과 목록을 요청합니다.", tags = { "Category Controller" })
    @Parameter(name = "major_id", description = "조회 할 학과를 major_id를 통해 요청")
    @Parameter(name = "parentCategory_id", description = "조회 할 학과가 소속된 단과대를 parentCategory_id 통해 요청")
    public ResponseEntity<?> findCategoryByMajorId(@PathVariable Long major_id) {
        return ResponseEntity
                .ok()
                .body(
                        ApiUtils.success(
                                categoryService.findCategoryByMajorId(major_id)
                        )
                );
    }
}
