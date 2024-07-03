package com.example.gasip.category.dto;

import com.example.gasip.category.model.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CategoryDto {

    private Long Id;
    private String collegeName;
    private String majorName;
    private List<CategoryDto> children;

    public static CategoryDto toEntity(Category category) {
        return CategoryDto.builder()
                .Id(category.getId())
                .collegeName(category.getCollegeName())
                .majorName(category.getMajorName())
                .children(category.getChildren()
                        .stream()
                        .map(CategoryDto::toEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}