package com.example.gasip.category.dto;

import com.example.gasip.category.model.Category;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@SuperBuilder
public class CategoryResponse {
    private Long Id;
    private String collegeName;
    private String majorName;
    private List<CategoryResponse> children;

    @QueryProjection
    public CategoryResponse(Long Id, String collegeName, String majorName) {
        this.Id = Id;
        this.collegeName = collegeName;
        this.majorName = majorName;
    }

    public static CategoryResponse fromEntity(Category category) {
        return CategoryResponse.builder()
                .Id(category.getId())
                .collegeName(category.getCollegeName())
                .majorName(category.getMajorName())
                .children(category.getChildren()
                        .stream()
                        .map(CategoryResponse::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }

}
