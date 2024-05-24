package com.example.gasip.professor.repository;

import com.example.gasip.professor.dto.ProfessorResponse;
import com.example.gasip.professor.dto.ProfessorWithBoardResponse;
import com.example.gasip.professor.dto.QProfessorWithBoardResponse;
import com.example.gasip.professor.dto.QProfessorResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.gasip.board.model.QBoard.board;
import static com.example.gasip.professor.model.QProfessor.professor;
@RequiredArgsConstructor
public class ProfessorRepositoryCustomImpl implements ProfessorRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ProfessorWithBoardResponse> findBoardByProfessor(Long profId) {
        return queryFactory
                .select(new QProfessorWithBoardResponse(
                        professor.profId, professor.profName, professor.category.Id, professor.category.majorName, board.content, board.postId, board.member.nickname))
                .from(professor, board)
                .where(professor.profId.eq(board.professor.profId), board.professor.profId.eq(profId))
                .fetch();
    }

    // 학과 키워드를 통한 교수 검색
    @Override
    public List<ProfessorResponse> findProfessorByCategoryNameContaining(String majorName) {
        return queryFactory
                .select(new QProfessorResponse(
                        professor.profId, professor.profName, professor.category.collegeName, professor.category.Id, professor.category.majorName))
                .from(professor)
                .where(professor.category.majorName.eq(majorName))
                .fetch();
    }
}
