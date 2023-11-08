package com.example.gasip.Boarddetail.repository;

import com.example.gasip.Boarddetail.model.BoardDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BoardDetailRepository extends JpaRepository<BoardDetail, Long> {

}
