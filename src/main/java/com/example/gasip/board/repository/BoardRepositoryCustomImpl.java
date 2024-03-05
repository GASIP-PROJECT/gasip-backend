package com.example.gasip.board.repository;

import com.example.gasip.board.dto.BoardContentDto;
import com.example.gasip.board.dto.BoardReadRequest;
import com.example.gasip.board.dto.BoardReadResponse;
import com.example.gasip.board.dto.QBoardReadResponse;
import com.example.gasip.board.model.Board;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.gasip.board.model.QBoard.board;
import static com.example.gasip.member.model.QMember.member;

@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<BoardContentDto> findContentsByMemberId(Long id) {

        return queryFactory
                .select(Projections.constructor(BoardContentDto.class, board.postId, board.content))
                .from(board)
                .leftJoin(board.member, member)
                .where(idEqual(id))
                .orderBy(board.postId.desc())
                .fetch();
    }

    @Override
    public List<BoardReadResponse> findAllBoard() {
        return queryFactory
                .select(new QBoardReadResponse(
                        board.regDate, board.updateDate, board.postId, board.content, board.clickCount, board.likeCount, board.professor.profId, board.gradePoint))
                .from(board)
                .fetch();
    }

    @Override
    public List<BoardReadRequest> findAllByPostId(Long postId) {
        return null;
    }

    /**
     *
     * 좋아요 기능
     */

    @Override
    public void addLikeCount(Board selectedBoard) {
        queryFactory.update(board)
                .set(board.likeCount, board.likeCount.add(1))
                .where(board.eq(selectedBoard))
                .execute();
    }

    @Override
    public void subLikeCount(Board selectedBoard) {
        queryFactory.update(board)
                .set(board.likeCount, board.likeCount.subtract(1))
                .where(board.eq(selectedBoard))
                .execute();
    }

    /**
     *
     * 조회수 기능
     */

    @Override
    public void addViewCount(Board selectedBoard) {
        queryFactory.update(board)
                .set(board.clickCount, board.clickCount.add(1))
                .where(board.eq(selectedBoard))
                .execute();
    }

    private BooleanExpression idEqual(Long id) {
        return (id == null) ? null : member.memberId.eq(id);
    }

}
