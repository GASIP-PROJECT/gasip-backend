package com.example.gasip.board.repository;

import com.example.gasip.board.dto.*;
import com.example.gasip.board.model.Board;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.gasip.board.model.QBoard.board;
import static com.example.gasip.member.model.QMember.member;
import static com.example.gasip.professor.model.QProfessor.professor;

@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<BoardReadResponse> findAllByMemberId(Long memberId,Pageable pageable) {
        return queryFactory
            .select(new QBoardReadResponse(
                board.regDate, board.updateDate, board.postId, board.member.nickname,
                board.content, board.clickCount, board.likeCount, board.professor.profId,
                board.gradePoint, board.professor.profName, board.professor.category.collegeName,
                board.professor.category.majorName))
            .from(board)
            .leftJoin(board.professor, professor)
            .where(board.member.memberId.eq(memberId))
            .orderBy(board.regDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
    }

    @Override
    public List<BoardReadResponse> findAllBoard() {
        return queryFactory
                .select(new QBoardReadResponse(
                        board.regDate, board.updateDate, board.postId, board.member.nickname,
                    board.content, board.clickCount, board.likeCount, board.professor.profId,
                    board.gradePoint, board.professor.profName, board.professor.category.collegeName,
                    board.professor.category.majorName))
                .from(board)
                .fetch();
    }

    @Override
    public List<BoardReadResponse> findByProfNameLike(String profName) {
        return queryFactory
                .select(new QBoardReadResponse(
                    board.regDate, board.updateDate, board.postId, board.member.nickname,
                    board.content, board.clickCount, board.likeCount, board.professor.profId,
                    board.gradePoint, board.professor.profName, board.professor.category.collegeName,
                    board.professor.category.majorName))
                .from(board)
                .leftJoin(board.professor, professor)
                .where(board.professor.profName.like(profName))
                .orderBy(board.regDate.desc())
                .fetch();
    }

    /**
     * 교수 게시글 정보 넘겨주기
     */
    @Override
    public List<BoardProfessorReadResponse> findBoarByProfessor(Long profId) {
        return queryFactory
                .select(new QBoardProfessorReadResponse(
                        board.regDate, board.updateDate, board.postId, board.content, board.clickCount,
                    board.likeCount, board.gradePoint, board.professor.profId, board.professor.profName,
                    board.professor.category.Id, board.professor.category.majorName, board.member.nickname))
                .from(board)
                .leftJoin(board.professor, professor)
                .where(board.professor.profId.eq(profId))
                .orderBy(board.regDate.desc())
                .fetch();
    }

    @Override
    public List<BoardReadResponse> findBestBoard(Pageable pageable) {
        return queryFactory
            .select(new QBoardReadResponse(
                board.regDate, board.updateDate, board.postId, board.member.nickname,
                board.content, board.clickCount, board.likeCount, board.professor.profId,
                board.gradePoint, board.professor.profName, board.professor.category.collegeName,
                board.professor.category.majorName))
            .from(board)
            .leftJoin(board.professor, professor)
            .where(board.likeCount.goe(5))
            .orderBy(board.regDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
    }

    @Override
    public List<BoardReadRequest> findAllByPostId(Long postId) {
        return null;
    }

    /**
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
