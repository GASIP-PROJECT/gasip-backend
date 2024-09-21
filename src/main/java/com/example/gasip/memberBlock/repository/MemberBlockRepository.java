package com.example.gasip.memberBlock.repository;

import com.example.gasip.member.model.Member;
import com.example.gasip.memberBlock.model.MemberBlock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberBlockRepository extends JpaRepository<MemberBlock, Long> {
    Optional<MemberBlock> findByBlockerAndBlocked(Member blocker, Member blocked);
}
