package com.example.gasip.board.dto;

import com.example.gasip.board.model.Board;
import com.example.gasip.global.entity.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
//@Builder
@SuperBuilder
@Schema(description = "게시글 세부사항 DTO 관련 VO")
public class BoardDetailResponse extends BaseTimeEntity {
    @Schema(description = "게시글 ID")
    private Long postId;
    @Schema(description = "게시글 내용")
    private String content;
    @Schema(description = "게시글 조회수")
    private Long clickCount;
    @Schema(description = "게시글 좋아요")
    private Long likeCount;
    @Schema(description = "게시글과 관련된 교수정보")
    private Long profId;

    public static BoardDetailResponse fromEntity(Board board) {
        return BoardDetailResponse.builder()
                .regDate(board.getRegDate())
                .updateDate(board.getUpdateDate())
                .postId(board.getPostId())
                .content(board.getContent())
                .clickCount(board.getClickCount())
                .likeCount(board.getLikeCount())
                .profId(board.getProfessor().getProfId())
                .build();
    }
}
