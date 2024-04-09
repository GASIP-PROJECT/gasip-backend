package com.example.gasip.board.repository;

import com.example.gasip.board.dto.BoardContentDto;
import com.example.gasip.board.dto.BoardReadRequest;
import com.example.gasip.board.dto.BoardReadResponse;
import com.example.gasip.board.model.Board;

import java.util.List;

public interface BoardRepositoryCustom {
    List<BoardContentDto> findContentsByMemberId(Long id);

    List<BoardReadResponse> findAllBoard();

    List<BoardReadRequest> findAllByPostId(Long postId);

    void addLikeCount(Board board);

    void subLikeCount(Board board);

    void addViewCount(Board board);

    List<BoardReadResponse> findProfNameLike(String profName);
}
