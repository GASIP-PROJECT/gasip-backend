package com.example.gasip.Boarddetail.dto;

import com.example.gasip.Board.basetime.BaseTimeEntity;
import com.example.gasip.Boarddetail.model.BoardDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
//@Builder
@SuperBuilder
public class BoardDetailResponse extends BaseTimeEntity {
    private Long postId;
    private String content;
    private Long clickCount;
    private Long likeCount;
    private Long profId;

    public static BoardDetailResponse fromEntity(BoardDetail boardDetail) {
        return BoardDetailResponse.builder()
                .regDate(boardDetail.getRegDate())
                .updateDate(boardDetail.getUpdateDate())
                .postId(boardDetail.getPostId())
                .content(boardDetail.getContent())
                .clickCount(boardDetail.getClickCount())
                .likeCount(boardDetail.getLikeCount())
                .profId(boardDetail.getProfId())
                .build();
    }
}
