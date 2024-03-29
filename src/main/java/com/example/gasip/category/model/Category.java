package com.example.gasip.category.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "단과대 ID")
    private Long Id;

    @Column(nullable = false)
    @Schema(description = "단과대 이름")
    private String collegeName;

    @Column(nullable = false, name = "major_name")
    @Schema(description = "전공 이름")
    private String majorName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentCategory_id")
    private Category parentCategory;

    @OneToMany (mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<Category> children = new ArrayList<>();


    @Builder
    public Category(Long Id, Long collegeId, String collegeName, Long majorId, String majorName, Category parentCategory) {
        this.Id = Id;
        this.collegeName = collegeName;
        this.majorName = majorName;
        this.parentCategory = parentCategory;
    }

}

