package com.example.gasip.category.dto;

import com.example.gasip.category.model.Category;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class CategoryResponse {
    private Long Id;
    private String collegeName;
    private String majorName;
    private Category parentCategory;

    @QueryProjection
    public CategoryResponse(Long Id, String collegeName, String majorName, Category parentCategory) {
        this.Id = Id;
        this.collegeName = collegeName;
        this.majorName = majorName;
        this.parentCategory = parentCategory;
    }

    public static CategoryResponse fromEntity(Category category) {
        return CategoryResponse.builder()
                .Id(category.getId())
                .collegeName(category.getCollegeName())
                .majorName(category.getMajorName())
                .parentCategory(category.getParentCategory())
                .build();
    }

}
