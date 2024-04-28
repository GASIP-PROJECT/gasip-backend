package com.example.gasip.grade.repository;

import com.example.gasip.grade.model.Grade;
import com.example.gasip.member.model.Member;
import com.example.gasip.professor.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade,Long>,GradeRepositoryCustom {

    Grade findByProfessorAndMember(Professor professor, Member member);

    Optional<Grade> findAllByProfessorAndMember(Professor professor, Member member);
}
