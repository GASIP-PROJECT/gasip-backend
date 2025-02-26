package com.example.gasip.board.repository;

import com.example.gasip.board.dto.BoardProfessorReadResponse;
import com.example.gasip.board.dto.BoardReadRequest;
import com.example.gasip.board.dto.BoardReadResponse;
import com.example.gasip.board.model.Board;
import com.example.gasip.professor.model.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface BoardRepositoryCustom {

    List<BoardReadResponse> findAllByMemberId(Long memberId,Pageable pageable);
    List<BoardReadResponse> findAllBoard();

    List<BoardReadRequest> findAllByPostId(Long postId);

    void addLikeCount(Board board);

    void subLikeCount(Board board);

    void addViewCount(Board board);

    void addReportCount(Board board);
    void subReportCount(Board board);

    List<BoardReadResponse> findBestBoard();

    /**
     * 교수 상세정보 넘기기
     */
    List<BoardProfessorReadResponse> findBoarByProfessor(Long profId);

    /**
     * 자유게시판 게시글 불러오기
     */
    Page<BoardReadResponse> findFreeBoardByProfessor(Long blockerId, Pageable pageable);

    /**
     * 자유게시판 제외한 모든 교수 게시글 불러오기
     */
//    Page<BoardReadResponse> findBoardByAllProfessor(Long blockerId, Pageable pageable);
    Slice<BoardReadResponse> findBoardByAllProfessorNoOffset(Long blockerId, Long lastPostId, Pageable pageable);

    /**
     * 게시글 내용 검색
     */
    Page<BoardReadResponse> findContainingContentOrderByRegDateDesc(Long blockerId, String content, Pageable pageable);

    /**
     * 교수 이름 검색
     */
    List<BoardReadResponse> findProfNameLike(String profName);

    /**
     * 교수 상세 페이지 게시글
     */
    Page<BoardReadResponse> findAllByProfessor(Long blockerId, Professor professor, Pageable pageable);
}
