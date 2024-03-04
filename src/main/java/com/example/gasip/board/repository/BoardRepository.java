package com.example.gasip.board.repository;

import com.example.gasip.board.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long>,BoardRepositoryCustom {
    Page<Board> findAllByOrderByRegDateDesc(Pageable pageable);

}
