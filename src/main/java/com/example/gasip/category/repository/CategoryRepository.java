package com.example.gasip.category.repository;

import com.example.gasip.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
    @Query("select c from Category c where c.parentCategory is NULL")
    List<Category> findCollege();

    @Query("select c from Category c where c.parentCategory is NULL")
    List<Category> findCategory();

    List<Category> findCategoryByParentCategory(Category parentCategory_id);

//    List<Category> findCategoryByMajorId(Long major_id);

}
