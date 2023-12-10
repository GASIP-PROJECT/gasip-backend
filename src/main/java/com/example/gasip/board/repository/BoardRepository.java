package com.example.gasip.board.repository;

import com.example.gasip.board.model.Board;
import com.example.gasip.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findByMemberOrderByPostIdDesc(Member member);

    @Query("SELECT new com.example.gasip.board.dto.BoardContentDTO(b.postId,b.content) FROM Board b WHERE b.member.id = :memberId order by b.postId DESC ")
    List<ArrayList<?>> findContentsByMemberIdOrderByPostIdDesc(Long memberId);
}
