package com.example.gasip.board.dto;

import com.example.gasip.board.model.Board;
import com.example.gasip.board.model.ContentActivity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardActivityResponseDto {
    @NotNull
    @Schema(description = "게시글 ID")
    private Long postId;
    @NotNull
    @Schema(description = "게시글 내용")
    private String content;
    @NotNull
    @Schema(description = "게시글과 관련된 교수 정보")
    private Long professor;
    @NotNull
    private Long reportCount;
    @NotNull
    private ContentActivity contentActivity;

    @Builder
    public BoardActivityResponseDto(Long postId, String content, Long professor, Long reportCount, ContentActivity contentActivity) {
        this.postId = postId;
        this.content = content;
        this.professor = professor;
        this.reportCount = reportCount;
        this.contentActivity = contentActivity;
    }

    public static BoardActivityResponseDto fromEntity(Board board) {
        return BoardActivityResponseDto.builder()
                .postId(board.getPostId())
                .content(board.getContent())
                .professor(board.getProfessor().getProfId())
                .contentActivity(board.getContentActivity())
                .build();
    }
}
