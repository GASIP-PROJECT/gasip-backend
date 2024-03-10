package com.example.gasip.board.controller;

import com.example.gasip.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class BoardControllerTest {

    @InjectMocks
    private BoardController boardController;
    @Mock
    private BoardService boardService;
    @Test
    void createBoard() {

    }

    @Test
    void findAllBoard() {
    }

    @Test
    void getBoardDetail() {
    }

    @Test
    void editBoard() {
    }

    @Test
    void deleteBoard() {
    }

    @Test
    void getBestBoard() {
    }
}