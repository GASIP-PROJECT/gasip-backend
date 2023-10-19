package com.example.gasip.repository;


import com.example.gasip.dto.ProfessorDto;

import com.example.gasip.entity.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
}

