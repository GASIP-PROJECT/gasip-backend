package com.example.gasip.profboard.dto;

import com.example.gasip.profboard.model.ProfBoard;
import com.example.gasip.global.entity.BaseTimeEntity;
import com.example.gasip.professor.model.Professor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Schema(description = "게시글 수정 Response DTO 관련 VO")
public class BoardUpdateResponse extends BaseTimeEntity {

    @NotNull
    @Schema(description = "게시글 ID")
    private Long postId;
    @NotNull
    @Schema(description = "게시글 내용")
    private String content;
    @Schema(description = "게시글 조회수")
    private Long clickCount;
    @Schema(description = "게시글 좋아요")
    private Long likeCount;
    @NotNull
    @Schema(description = "게시글과 관련된 교수 정보")
    private Professor professor;

    @Builder
    public BoardUpdateResponse(Long postId, String content, Long clickCount, Long likeCount, LocalDateTime regDate, LocalDateTime updateDate, Professor professor) {
        this.postId = postId;
        this.content = content;
        this.clickCount = clickCount;
        this.likeCount = likeCount;
        this.professor = professor;
    }

    public static BoardUpdateResponse fromEntity(ProfBoard profBoard) {
        return BoardUpdateResponse.builder()
                .postId(profBoard.getPostId())
                .content(profBoard.getContent())
                .clickCount(profBoard.getClickCount())
                .likeCount(profBoard.getLikeCount())
                .regDate(profBoard.getRegDate())
                .updateDate(profBoard.getUpdateDate())
                .build();
    }
}
