package com.example.gasip.profboard.repository;

import com.example.gasip.profboard.dto.BoardContentDto;
import com.example.gasip.profboard.dto.BoardReadRequest;
import com.example.gasip.profboard.dto.BoardReadResponse;
import com.example.gasip.profboard.dto.QBoardReadResponse;
import com.example.gasip.profboard.model.ProfBoard;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.gasip.profboard.model.QBoard.board;
import static com.example.gasip.member.model.QMember.member;

@RequiredArgsConstructor
public class ProfProfBoardRepositoryCustomImpl implements ProfBoardRepositoryCustom {

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
                        board.regDate, board.updateDate, board.postId, board.content, board.clickCount, board.likeCount, board.professor.profId))
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
    public void addLikeCount(ProfBoard selectedProfBoard) {
        queryFactory.update(board)
                .set(board.likeCount, board.likeCount.add(1))
                .where(board.eq(selectedProfBoard))
                .execute();
    }

    @Override
    public void subLikeCount(ProfBoard selectedProfBoard) {
        queryFactory.update(board)
                .set(board.likeCount, board.likeCount.subtract(1))
                .where(board.eq(selectedProfBoard))
                .execute();
    }

    /**
     *
     * 조회수 기능
     */

    @Override
    public void addViewCount(ProfBoard selectedProfBoard) {
        queryFactory.update(board)
                .set(board.clickCount, board.clickCount.add(1))
                .where(board.eq(selectedProfBoard))
                .execute();
    }

    private BooleanExpression idEqual(Long id) {
        return (id == null) ? null : member.memberId.eq(id);
    }

}
