package com.example.gasip.Boarddetail.service;

import com.example.gasip.Boarddetail.dto.BoardDetailResponse;
import com.example.gasip.Boarddetail.model.BoardDetail;
import com.example.gasip.Boarddetail.repository.BoardDetailRepository;
import com.example.gasip.professordetail.dto.ProfessorDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDetailService {
    private final BoardDetailRepository boardDetailRepository;

    public BoardDetailResponse findByID(Long postId) {
        BoardDetail boardDetail = boardDetailRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        return BoardDetailResponse.fromEntity(boardDetail);
    }
}
