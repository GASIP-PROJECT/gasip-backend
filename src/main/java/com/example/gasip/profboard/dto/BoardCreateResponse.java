package com.example.gasip.profboard.dto;

import com.example.gasip.profboard.model.ProfBoard;
import com.example.gasip.global.entity.BaseTimeEntity;
import com.example.gasip.professor.model.Professor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@SuperBuilder
@Schema(description = "게시글 생성 Response DTO 관련 VO")
public class BoardCreateResponse extends BaseTimeEntity {

    @NotNull
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
    private Long profId;

    public BoardCreateResponse(LocalDateTime regDate, LocalDateTime updateDate, Long postId, String content, Professor professor) {
        super(regDate, updateDate);
        this.postId = postId;
        this.content = content;
        this.clickCount = 0L;
        this.likeCount = 0L;
        this.profId = professor.getProfId();
    }

    //fromEntity메서드 개발
    public static BoardCreateResponse fromEntity(ProfBoard profBoard) {
        return BoardCreateResponse.builder()
                .regDate(profBoard.getRegDate())
                .updateDate(profBoard.getUpdateDate())
                .postId(profBoard.getPostId())
                .content(profBoard.getContent())
                .clickCount(0L)
                .likeCount(0L)
                .profId(profBoard.getProfessor().getProfId())
                .build();
    }


}
