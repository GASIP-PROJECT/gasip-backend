package com.example.gasip.board.dto;

import com.example.gasip.global.entity.BaseTimeEntity;
import com.example.gasip.board.model.Board;
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
    @Schema(description = "교수 평점")
    private int gradePoint;

    public BoardCreateResponse(LocalDateTime regDate, LocalDateTime updateDate, Long postId, String content, Professor professor,int gradePoint) {
        super(regDate, updateDate);
        this.postId = postId;
        this.content = content;
        this.clickCount = 0L;
        this.likeCount = 0L;
        this.profId = professor.getProfId();
        this.gradePoint = gradePoint;
    }

    public BoardCreateResponse(Long postId, String content,Long profId, int gradePoint) {
        this.postId = postId;
        this.content = content;
        this.profId = profId;
        this.gradePoint = gradePoint;
    }

    //fromEntity메서드 개발
    public static BoardCreateResponse fromEntity(Board board) {
        return BoardCreateResponse.builder()
                .regDate(board.getRegDate())
                .updateDate(board.getUpdateDate())
                .postId(board.getPostId())
                .content(board.getContent())
                .clickCount(0L)
                .likeCount(0L)
                .profId(board.getProfessor().getProfId())
                .gradePoint(board.getGradePoint())
                .build();
    }


}
