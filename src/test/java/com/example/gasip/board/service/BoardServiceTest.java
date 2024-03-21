package com.example.gasip.board.service;

import com.example.gasip.board.model.Board;
import com.example.gasip.board.repository.BoardRepository;
import com.example.gasip.member.repository.MemberRepository;
import com.example.gasip.professor.repository.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@SpringBootTest
class BoardServiceTest {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void createBoard() {
        Board board = Board.builder()
            .content("content")
            .clickCount(0L)
            .likeCount(0L)
            .gradePoint(3)
            .build();
        Board savedBoard = boardRepository.save(board);
        assertThat(board).isSameAs(savedBoard);
    }

    @Test
    void findAllBoard() {
    }

    @Test
    void findById() {
    }

    @Test
    void findBoardId() {
    }

    @Test
    void editBoard() {
    }

    @Test
    void deleteBoard() {
    }

    @Test
    void findBestBoard() {
    }
}