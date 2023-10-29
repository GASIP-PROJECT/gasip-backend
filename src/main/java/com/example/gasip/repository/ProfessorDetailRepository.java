package com.example.gasip.repository;

import com.example.gasip.entity.ProfessorDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorDetailRepository extends JpaRepository<ProfessorDetail,Long> {
}
