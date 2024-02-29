package com.example.gasip.profboard.service;


import com.example.gasip.profboard.dto.*;
import com.example.gasip.profboard.model.ProfBoard;
import com.example.gasip.profboard.repository.ProfBoardRepository;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.member.model.Member;
import com.example.gasip.member.repository.MemberRepository;
import com.example.gasip.professor.model.Professor;
import com.example.gasip.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final ProfBoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ProfessorRepository professorRepository;

    @Transactional
    public BoardCreateResponse createBoard(BoardCreateRequest boardCreateRequest, MemberDetails memberDetails, Long profId) {
        Member member = memberRepository.getReferenceById(memberDetails.getId());
        Professor professor = professorRepository.findById(profId).orElseThrow(IllegalArgumentException::new);
        ProfBoard profBoard = boardRepository.save(boardCreateRequest.toEntity(professor,member));
        return BoardCreateResponse.fromEntity(profBoard);
    }

    @Transactional(readOnly = true)
    public List<BoardReadResponse> findAllBoard(Pageable pageable) {
        List<BoardReadResponse> boardReadResponseList = boardRepository.findAllByOrderByRegDateDesc(pageable)
            .stream()
            .map(BoardReadResponse::fromEntity)
            .collect(Collectors.toList());
        return boardReadResponseList;
    }
    @Transactional
    public BoardDetailResponse findById(Long postId) {
        ProfBoard profBoard = boardRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        return BoardDetailResponse.fromEntity(profBoard);
    }

    @Transactional
    public BoardReadResponse findBoardId(Long postId) {
        ProfBoard profBoard = boardRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        insertView(postId);
        return BoardReadResponse.fromEntity(profBoard);
    }
    @Transactional
    public BoardUpdateResponse editBoard(MemberDetails memberDetails,Long boardId,BoardUpdateRequest boardUpdateRequest) {
        ProfBoard profBoard = validatedBoardWritter(memberDetails, boardId);
        profBoard.updateBoard(boardUpdateRequest.getContent());
        return BoardUpdateResponse.fromEntity(profBoard);
    }
    @Transactional
    public String deleteBoard(MemberDetails memberDetails,Long boardId) {
        validatedBoardWritter(memberDetails, boardId);
        boardRepository.deleteById(boardId);
        return boardId + "번 게시글이 삭제되었습니다.";
    }

    private ProfBoard validatedBoardWritter(MemberDetails memberDetails, Long boardId) {
        ProfBoard profBoard = validateBoardEmpty(boardId);
        Member member = memberRepository.getReferenceById(memberDetails.getId());
        if (!member.getMemberId().equals(profBoard.getMember().getMemberId())) {
            throw new IllegalArgumentException();
        }
        return profBoard;
    }

    private ProfBoard validateBoardEmpty(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(
                IllegalArgumentException::new
        );
    }

    /**
     *
     * 조회수
     */
    @Transactional
    public void insertView(Long postId) {

//        Member member = memberRepository.findById(boardReadResponse.getMemberId())
//                .orElseThrow(() -> new NotFoundException("Could not found member id : " + boardReadResponse.getMemberId()));

        ProfBoard profBoard = boardRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + postId));

//        // 이미 좋아요되어있으면 에러 반환
//        if (boardRepository.findAllByPostId(boardReadRequest.getPostId()).equals(board.getPostId())){
//            //TODO 409에러로 변경
//            throw new NotFoundException("Wrong postId : " + boardReadRequest.getPostId());
//        }

//        Likes likes = Likes.builder()
//                .board(board)
//                .build();

        boardRepository.save(profBoard);
        boardRepository.addViewCount(profBoard);
    }

    public List<BoardReadResponse> findProfBoardDetail(Long profId,Pageable pageable) {
        Professor professor = professorRepository.findById(profId).orElseThrow(
            () -> new IllegalArgumentException()
        );
        List<BoardReadResponse> boardReadResponseList = boardRepository.findAllByProfessorOrderByRegDateDesc(professor,pageable)
            .stream()
            .map(BoardReadResponse::fromEntity)
            .collect(Collectors.toList());
        return boardReadResponseList;
    }
}


