package com.example.gasip.professor.repository;


import com.example.gasip.category.model.Category;
import com.example.gasip.major.model.Major;
import com.example.gasip.professor.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    List<Professor> findProfessorByMajor(Major majorId);
}

