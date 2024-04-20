package com.example.gasip.likes.repository;

import com.example.gasip.board.model.Board;
import com.example.gasip.likes.model.Likes;
import com.example.gasip.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByMemberAndBoard(Member member, Board board);

    Boolean existsByBoard_PostIdAndMember_MemberId(Long boardId, Long memberId);
}
