package com.example.gasip.category.repository;

import com.example.gasip.category.model.Category;
import com.example.gasip.major.model.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c where c.parentCategory is NULL")
//    @Query("SELECT c FROM Category c LEFT JOIN c.parentCategory p ORDER BY p.collegeId ASC NULLS FIRST, c.collegeId ASC")
    List<Category> findCategory();

    List<Category> findCategoryByParentCategory(Category parentCategory_id);

}
