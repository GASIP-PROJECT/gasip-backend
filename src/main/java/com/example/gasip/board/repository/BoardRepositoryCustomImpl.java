package com.example.gasip.board.repository;

import com.example.gasip.board.dto.*;
import com.example.gasip.board.model.Board;
import com.example.gasip.board.model.ContentActivity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.gasip.board.model.QBoard.board;
import static com.example.gasip.member.model.QMember.member;
import static com.example.gasip.memberBlock.model.QMemberBlock.memberBlock;
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
                board.professor.profName, board.professor.category.collegeName,
                board.professor.category.majorName, board.contentActivity))
            .from(board)
            .leftJoin(board.professor, professor)
            .where(board.member.memberId.eq(memberId).and(board.contentActivity.eq(ContentActivity.GENERAL)))
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
                    board.professor.profName, board.professor.category.collegeName,
                    board.professor.category.majorName, board.contentActivity))
                .from(board)
                .fetch();
    }

    /**
     *
     * 자유 게시판 게시글 불러오기
     */
    @Override
    public Page<BoardReadResponse> findFreeBoardByProfessor(Long blockerId, Pageable pageable) {
        List<Long> ids = queryFactory
                .select(board.postId)
                .from(board)
                .leftJoin(board.professor, professor)
                .where(board.professor.profId.eq(0L).and(board.contentActivity.eq(ContentActivity.GENERAL)))
                .fetch();

        List<Long> blockedIds = queryFactory
                .select(memberBlock.blocked.memberId)
                .from(memberBlock)
                .where(memberBlock.blocker.memberId.eq(blockerId))
                .fetch();

        List<BoardReadResponse> boardReadResponses = queryFactory
                .select(new QBoardReadResponse(
                        board.regDate, board.updateDate, board.postId, board.member.nickname,
                        board.content, board.clickCount, board.likeCount, board.professor.profId,
                        board.professor.profName, board.professor.category.collegeName,
                        board.professor.category.majorName, board.contentActivity))
                .from(board)
                .where(board.postId.in(ids).and(board.member.memberId.notIn(blockedIds)))
                .orderBy(board.regDate.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
        return new PageImpl<>(boardReadResponses);
    }

    /**
     *
     * 자유게시글 제외한 모든 교수 리뷰 불러오기
     *
     */
    @Override
    public Page<BoardReadResponse> findBoardByAllProfessor(Long blockerId, Pageable pageable) {
        List<Long> prof_ids = queryFactory
                .select(board.postId)
                .from(board)
                .leftJoin(board.professor, professor)
                .where(board.professor.profId.gt(0L).and(board.contentActivity.eq(ContentActivity.GENERAL)))
                .fetch();

        List<Long> blockedIds = queryFactory
                .select(memberBlock.blocked.memberId)
                .from(memberBlock)
                .where(memberBlock.blocker.memberId.eq(blockerId))
                .fetch();

        List<BoardReadResponse> boardReadResponses = queryFactory
                .select(new QBoardReadResponse(
                        board.regDate, board.updateDate, board.postId, board.member.nickname,
                        board.content, board.clickCount, board.likeCount, board.professor.profId,
                        board.professor.profName, board.professor.category.collegeName,
                        board.professor.category.majorName, board.contentActivity))
                .from(board)
//                .leftJoin(board.professor, professor)
//                .where(board.professor.profId.gt(0))
                .where(board.postId.in(prof_ids).and(board.member.memberId.notIn(blockedIds)))
                .orderBy(board.regDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(boardReadResponses);
    }

    /**
     * 게시글 내용 검색 (querydsl)
     */
    @Override
    public Page<BoardReadResponse> findContainingContentOrderByRegDateDesc(String content, Pageable pageable) {
        List<BoardReadResponse> boardReadResponses = queryFactory
                .select(new QBoardReadResponse(
                        board.regDate, board.updateDate, board.postId, board.member.nickname,
                        board.content, board.clickCount, board.likeCount, board.professor.profId,
                        board.professor.profName, board.professor.category.collegeName,
                        board.professor.category.majorName, board.contentActivity))
                .from(board)
                .leftJoin(board.professor, professor)
                .where(board.content.contains(content).and(board.contentActivity.eq(ContentActivity.GENERAL)))
                .orderBy(board.regDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(boardReadResponses);
    }

    /**
     * 교수 이름 검색
     */
    @Override
    public List<BoardReadResponse> findProfNameLike(String profName) {
        return queryFactory
                .select(new QBoardReadResponse(
                    board.regDate, board.updateDate, board.postId, board.member.nickname,
                    board.content, board.clickCount, board.likeCount, board.professor.profId,
                    board.professor.profName, board.professor.category.collegeName,
                    board.professor.category.majorName, board.contentActivity))
                .from(board)
                .leftJoin(board.professor, professor)
                .where(board.professor.profName.like(profName).and(board.contentActivity.eq(ContentActivity.GENERAL)))
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
                    board.likeCount, board.professor.profId, board.professor.profName,
                    board.professor.category.Id, board.professor.category.majorName, board.member.nickname))
                .from(board)
                .leftJoin(board.professor, professor)
                .where(board.professor.profId.eq(profId).and(board.contentActivity.eq(ContentActivity.GENERAL)))
                .orderBy(board.regDate.desc())
                .fetch();
    }

    @Override
    public List<BoardReadResponse> findBestBoard() {
        return queryFactory
            .select(new QBoardReadResponse(
                board.regDate, board.updateDate, board.postId, board.member.nickname,
                board.content, board.clickCount, board.likeCount, board.professor.profId,
                board.professor.profName, board.professor.category.collegeName,
                board.professor.category.majorName, board.contentActivity))
            .from(board)
            .leftJoin(board.professor, professor)
            .where(board.likeCount.goe(5).and(board.contentActivity.eq(ContentActivity.GENERAL)))
            .orderBy(board.regDate.desc())
            .limit(30)
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

    /**
     * 신고 관련 기능
     */
    @Override
    public void addReportCount(Board selectedBoard) {
        queryFactory.update(board)
                .set(board.reportCount, board.reportCount.add(1))
                .where(board.eq(selectedBoard))
                .execute();
    }

    @Override
    public void subReportCount(Board selectedBoard) {
        queryFactory.update(board)
                .set(board.reportCount, board.reportCount.subtract(1))
                .where(board.eq(selectedBoard))
                .execute();
    }


    private BooleanExpression idEqual(Long id) {
        return (id == null) ? null : member.memberId.eq(id);
    }


}
