package com.example.gasip.Board.service;

import com.example.gasip.Board.dto.*;
import com.example.gasip.Board.model.Board;
import com.example.gasip.Board.repository.BoardRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 post
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
    public BoardUpdateResponse editBoard(Long boardId, @Valid BoardUpdateRequest boardUpdateRequest) {
        return null;
    }
    @Transactional
    public String deleteBoard(Long boardId) {
        return "";
    }
}
