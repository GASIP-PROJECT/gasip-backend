package com.example.gasip.profboard.dto;

import com.example.gasip.profboard.model.ProfBoard;
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

    public static BoardDetailResponse fromEntity(ProfBoard profBoard) {
        return BoardDetailResponse.builder()
                .regDate(profBoard.getRegDate())
                .updateDate(profBoard.getUpdateDate())
                .postId(profBoard.getPostId())
                .content(profBoard.getContent())
                .clickCount(profBoard.getClickCount())
                .likeCount(profBoard.getLikeCount())
                .profId(profBoard.getProfessor().getProfId())
                .build();
    }
}
