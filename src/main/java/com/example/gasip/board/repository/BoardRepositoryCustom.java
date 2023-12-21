package com.example.gasip.board.repository;

import com.example.gasip.board.dto.BoardContentDto;
import com.example.gasip.board.dto.BoardReadResponse;

import java.util.List;

public interface BoardRepositoryCustom {
    List<BoardContentDto> findContentsByMemberId(Long id);

    List<BoardReadResponse> findAllBoard();
}
