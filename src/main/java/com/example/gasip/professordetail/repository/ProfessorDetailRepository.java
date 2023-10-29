package com.example.gasip.professordetail.repository;

import com.example.gasip.professordetail.model.ProfessorDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorDetailRepository extends JpaRepository<ProfessorDetail,Long> {
}
