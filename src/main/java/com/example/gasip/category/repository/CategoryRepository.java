package com.example.gasip.category.repository;

import com.example.gasip.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
    @Query("select c from Category c where c.parentCategory is NULL")
    List<Category> findCategory();

    //TODO : 전체 카테고리 제외 한 결과 반환하도록 querydsl로 리팩토링
    List<Category> findCategoryByParentCategory(Category parentCategory_id);

    List<Category> findCategoryById(Long Id);

    Category findByMajorName(String majorName);

}
