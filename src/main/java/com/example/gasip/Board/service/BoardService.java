package com.example.gasip.Board.service;

import com.example.gasip.Board.dto.BoardCreateRequest;
import com.example.gasip.Board.dto.BoardCreateResponse;
import com.example.gasip.Board.dto.BoardUpdateRequest;
import com.example.gasip.Board.dto.BoardUpdateResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BoardService {
    @Transactional
    public BoardCreateResponse createBoard(BoardCreateRequest boardCreateRequest) {
        return null;
    }
    @Transactional(readOnly = true)
    public BoardCreateResponse findAllBoard() {
        return null;
    }
    @Transactional
    public BoardUpdateResponse editBoard(Long boardId, @Valid BoardUpdateRequest boardUpdateRequest) {
        return null;
    }
    @Transactional
    public String deleteBoard(Long boardId) {
        return "";
    }
}
