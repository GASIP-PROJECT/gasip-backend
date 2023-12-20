package com.example.gasip.board.repository;

import com.example.gasip.board.dto.BoardContentDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.gasip.board.model.QBoard.board;
import static com.example.gasip.member.model.QMember.member;

@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    @Override
    public List<BoardContentDto> findContentsByMemberId(Long id) {

        return queryFactory
            .select(Projections.constructor(BoardContentDto.class,board.postId, board.content))
            .from(board)
            .leftJoin(board.member, member)
            .where(idEqual(id))
            .orderBy(board.postId.desc())
            .fetch();
    }

    private BooleanExpression idEqual(Long id) {
        return (id == null) ? null : member.memberId.eq(id);
    }
}
