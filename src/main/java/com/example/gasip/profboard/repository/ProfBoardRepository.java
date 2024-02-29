package com.example.gasip.profboard.repository;

import com.example.gasip.profboard.model.ProfBoard;
import com.example.gasip.professor.model.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfBoardRepository extends JpaRepository<ProfBoard,Long>, ProfBoardRepositoryCustom {
    Page<ProfBoard> findAllByOrderByRegDateDesc(Pageable pageable);

    Page<ProfBoard> findAllByProfessorOrderByRegDateDesc(Professor professor, Pageable pageable);

}
