package com.example.gasip.professor.repository;


import com.example.gasip.category.model.Category;
import com.example.gasip.professor.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>, ProfessorRepositoryCustom {
    List<Professor> findProfessorByCategory(Category Id);

    List<Professor> findProfessorByProfNameLike(String profName);

    Professor findByProfNameAndCategory(String profName,Category category);
}

