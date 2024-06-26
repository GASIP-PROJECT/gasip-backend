package com.example.gasip.board.repository;

import com.example.gasip.board.model.Board;
import com.example.gasip.professor.model.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long>,BoardRepositoryCustom {
    Page<Board> findAllByOrderByRegDateDesc(Pageable pageable);
    // 게시글 내용 검색
    Page<Board> findByContentContainingOrderByRegDateDesc(String content, Pageable pageable);

    Page<Board> findAllByProfessorOrderByRegDateDesc(Professor professor, Pageable pageable);

    List<Board> findByProfessorProfNameLike(String profName);
}
