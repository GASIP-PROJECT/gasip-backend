package com.example.gasip.professor.repository;


import com.example.gasip.professor.dto.ProfessorDto;
import com.example.gasip.professor.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}

