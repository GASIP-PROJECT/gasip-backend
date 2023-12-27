package com.example.gasip.board.service;


import com.example.gasip.board.dto.*;
import com.example.gasip.board.model.Board;
import com.example.gasip.board.repository.BoardRepository;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.member.model.Member;
import com.example.gasip.member.repository.MemberRepository;
import com.example.gasip.professor.model.Professor;
import com.example.gasip.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ProfessorRepository professorRepository;

    @Transactional
    public BoardCreateResponse createBoard(BoardCreateRequest boardCreateRequest, MemberDetails memberDetails, Long profId) {
        Member member = memberRepository.getReferenceById(memberDetails.getId());
        Professor professor = professorRepository.findById(profId).orElseThrow(IllegalArgumentException::new);
        Board board = boardRepository.save(boardCreateRequest.toEntity(professor,member));
        return BoardCreateResponse.fromEntity(board);
    }

    @Transactional(readOnly = true)
    public List<BoardReadResponse> findAllBoard() {
        return boardRepository.findAll()
            .stream()
            .map(BoardReadResponse::fromEntity)
            .collect(Collectors.toList());
    }
    @Transactional
    public BoardDetailResponse findById(Long postId) {
        Board board = boardRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        return BoardDetailResponse.fromEntity(board);
    }

    @Transactional
    public BoardReadResponse findBoardId(Long postId) {
        Board board = boardRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        return BoardReadResponse.fromEntity(board);
    }
    @Transactional
    public BoardUpdateResponse editBoard(MemberDetails memberDetails,Long boardId,BoardUpdateRequest boardUpdateRequest) {
        Board board = validatedBoardWritter(memberDetails, boardId);
        board.updateBoard(boardUpdateRequest.getContent());
        return BoardUpdateResponse.fromEntity(board);
    }
    @Transactional
    public String deleteBoard(MemberDetails memberDetails,Long boardId) {
        validatedBoardWritter(memberDetails, boardId);
        boardRepository.deleteById(boardId);
        return boardId + "번 게시글이 삭제되었습니다.";
    }

    private Board validatedBoardWritter(MemberDetails memberDetails, Long boardId) {
        Board board = validateBoardEmpty(boardId);
        Member member = memberRepository.getReferenceById(memberDetails.getId());
        if (!member.getMemberId().equals(board.getMember().getMemberId())) {
            throw new IllegalArgumentException();
        }
        return board;
    }

    private Board validateBoardEmpty(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(
                IllegalArgumentException::new
        );
    }
}


