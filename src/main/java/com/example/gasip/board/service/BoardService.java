package com.example.gasip.board.service;

import com.example.gasip.board.dto.*;
import com.example.gasip.board.model.Board;
import com.example.gasip.board.repository.BoardRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardCreateResponse createBoard(BoardCreateRequest boardCreateRequest) {
        Board board = boardRepository.save(boardCreateRequest.toEntity(boardCreateRequest));
        return BoardCreateResponse.fromEntity(board);
    }

    @Transactional(readOnly = true)
    public List<BoardReadResponse> findAllBoard() {
        List<Board> boards = boardRepository.findAll();
        List<BoardReadResponse> boardList = new ArrayList<>();
        for (Board board : boards) {
            boardList.add(BoardReadResponse.fromEntity(board));
        }
        return boardList;
    }

    @Transactional
    public BoardReadResponse findBoardId(Long postId) {
        Board board = boardRepository.findById(postId)
                .orElseThrow(IllegalArgumentException::new);
        return BoardReadResponse.fromEntity(board);
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


