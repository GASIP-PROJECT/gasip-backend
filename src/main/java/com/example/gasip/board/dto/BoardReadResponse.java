package com.example.gasip.board.dto;

import com.example.gasip.board.model.Board;
import com.example.gasip.global.entity.BaseTimeEntity;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@SuperBuilder
@Schema(description = "게시글 읽기 Response DTO 관련 VO")
@AllArgsConstructor
public class BoardReadResponse extends BaseTimeEntity {
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
    private Long profId;
    @Schema(description = "교수 평점")
    private int gradePoint;
    @Schema(description = "교수 이름")
    private String profName;
    @Schema(description = "소속 단과대 이름")
    private String collegeName;
    @Schema(description = "소속 학과 이름")
    private String majorName;
    @Schema(description = "닉네임")
    private String nickname;
    @Schema(description = "좋아요 클릭 여부")
    private Boolean isLike;

    @QueryProjection
    public BoardReadResponse(LocalDateTime regDate, LocalDateTime updateDate, Long postId, String content,
                             Long clickCount, Long likeCount, Long profId, int gradePoint, String profName,
                             String collegeName,String majorName, String nickname
    ) {
        super(regDate, updateDate);
        this.postId = postId;
        this.content = content;
        this.clickCount = clickCount;
        this.likeCount = likeCount;
        this.profId = profId;
        this.gradePoint = gradePoint;
        this.profName = profName;
        this.collegeName = collegeName;
        this.majorName = majorName;
        this.nickname = nickname;
    }
    public static BoardReadResponse fromEntity(Board board) {
        return BoardReadResponse.builder()
            .regDate(board.getRegDate())
            .updateDate(board.getUpdateDate())
            .postId(board.getPostId())
            .content(board.getContent())
            .clickCount(board.getClickCount())
            .likeCount(board.getLikeCount())
            .profId(board.getProfessor().getProfId())
            .profName(board.getProfessor().getProfName())
            .collegeName(board.getProfessor().getCategory().getCollegeName())
            .majorName(board.getProfessor().getCategory().getMajorName())
            .nickname(board.getMember().getNickname())
            .isLike(board.getIsLike())
            .build();
    }
}
