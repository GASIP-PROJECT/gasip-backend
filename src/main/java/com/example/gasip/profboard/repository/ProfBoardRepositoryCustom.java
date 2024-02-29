package com.example.gasip.profboard.repository;

import com.example.gasip.profboard.dto.BoardContentDto;
import com.example.gasip.profboard.dto.BoardReadRequest;
import com.example.gasip.profboard.dto.BoardReadResponse;
import com.example.gasip.profboard.model.ProfBoard;

import java.util.List;

public interface ProfBoardRepositoryCustom {
    List<BoardContentDto> findContentsByMemberId(Long id);

    List<BoardReadResponse> findAllBoard();

    List<BoardReadRequest> findAllByPostId(Long postId);

    void addLikeCount(ProfBoard profBoard);

    void subLikeCount(ProfBoard profBoard);

    void addViewCount(ProfBoard profBoard);
}
