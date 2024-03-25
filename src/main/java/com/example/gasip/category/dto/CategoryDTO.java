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
public class CategoryDTO {

    private Long Id;
    private Long collegeId;
    private String collegeName;
    private Long majorId;
    private String majorName;
    private List<CategoryDTO> children;

//    public CategoryDTO (Category entity) {
//
//        this.collegeId = entity.getCollegeId();
//        this.collegeName = entity.getCollegeName();
//        this.majorId = entity.getMajorId();
//        this.majorName = entity.getMajorName();
//        if(entity.getParentCategory() == null) {
//
//            this.parentCategoryName = "대분류";
//
//        } else {
//
//            this.parentCategoryName = entity.getParentCategory().getMajorName();
//
//        }
//
//        this.children = entity.getSubCategory() == null ? null :
//                entity.getSubCategory().stream().collect(Collectors.toMap(
//                        Category::getMajorName(), CategoryDTO::new
//
//        ));
//    }

    public static CategoryDTO toEntity(Category category) {
        return CategoryDTO.builder()
                .Id(category.getId())
                .collegeId(category.getCollegeId())
                .collegeName(category.getCollegeName())
                .majorId(category.getMajorId())
                .majorName(category.getMajorName())
                .children(category.getChildren().stream().map(CategoryDTO::toEntity).collect(Collectors.toList()))
                .build();
    }
}