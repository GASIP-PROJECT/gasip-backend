package com.example.gasip.major.repository;

import com.example.gasip.college.model.College;
import com.example.gasip.major.dto.MajorResponse;
import com.example.gasip.major.model.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {
}
