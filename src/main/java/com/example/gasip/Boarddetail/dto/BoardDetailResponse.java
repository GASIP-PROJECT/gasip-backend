package com.example.gasip.Boarddetail.dto;

import com.example.gasip.Boarddetail.model.BoardDetail;
import com.example.gasip.professordetail.dto.ProfessorDetailResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class BoardDetailResponse {
    private Long postId;
    private String content;
    private Long clickCount;
    private Long likeCount;
    private Long profId;

    public static BoardDetailResponse fromEntity(BoardDetail boardDetail) {
        return BoardDetailResponse.builder()
                .postId(boardDetail.getPostId())
                .content(boardDetail.getContent())
                .clickCount(boardDetail.getClickCount())
                .likeCount(boardDetail.getLikeCount())
                .profId(boardDetail.getProfId())
                .build();
    }
}
