package com.example.gasip.board.service;

import com.example.gasip.board.dto.*;
import com.example.gasip.board.model.Board;
import com.example.gasip.board.repository.BoardRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    @Transactional
    public BoardCreateResponse createBoard(BoardCreateRequest boardCreateRequest) {
        return null;
    }
    @Transactional(readOnly = true)
    public BoardCreateResponse findAllBoard() {
        return null;
    }
    @Transactional
    public BoardUpdateResponse editBoard(Long boardId,  @Valid BoardUpdateRequest boardUpdateRequest) {
        Board board = validateBoardEmpty(boardId);
        board.updateBoard(boardUpdateRequest.getContent());
        return BoardUpdateResponse.fromEntity(board);
    }
    @Transactional
    public String deleteBoard(Long boardId) {
        validateBoardEmpty(boardId);
        boardRepository.deleteById(boardId);
        return boardId + "번 게시글이 삭제되었습니다.";
    }

    private Board validateBoardEmpty(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(
                IllegalArgumentException::new
        );
    }
}


