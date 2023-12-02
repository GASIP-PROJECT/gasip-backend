package com.example.gasip.Boarddetail.dto;

import com.example.gasip.common.BaseTimeEntity;
import com.example.gasip.Boarddetail.model.BoardDetail;
import com.example.gasip.professor.model.Professor;
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
    private Professor professor;

    public static BoardDetailResponse fromEntity(BoardDetail boardDetail) {
        return BoardDetailResponse.builder()
                .regDate(boardDetail.getRegDate())
                .updateDate(boardDetail.getUpdateDate())
                .postId(boardDetail.getPostId())
                .content(boardDetail.getContent())
                .clickCount(boardDetail.getClickCount())
                .likeCount(boardDetail.getLikeCount())
                .professor(boardDetail.getProfessor())
                .build();
    }
}
