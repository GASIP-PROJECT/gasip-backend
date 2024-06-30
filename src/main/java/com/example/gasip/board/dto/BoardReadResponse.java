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

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@SuperBuilder
@Schema(description = "게시글 읽기 Response DTO 관련 VO")
@AllArgsConstructor
public class BoardReadResponse extends BaseTimeEntity implements Serializable {
    @NotNull
    @Schema(description = "게시글 ID")
    private Long postId;
    @NotNull
    @Schema(description = "게시글 작성자")
    private String memberNickname;
    @NotNull
    @Schema(description = "게시글 내용")
    private String content;
    @NotNull
    @Schema(description = "게시글 조회수")
    private Long clickCount;
    @NotNull
    @Schema(description = "게시글 좋아요")
    private Long likeCount;
    @NotNull
    @Schema(description = "게시글에 달린 댓글 개수")
    private Long commentCount;
    @NotNull
    @Schema(description = "게시글과 관련된 교수 정보")
    private Long profId;
    @NotNull
    @Schema(description = "교수 이름")
    private String profName;
    @NotNull
    @Schema(description = "소속 단과대 이름")
    private String collegeName;
    @NotNull
    @Schema(description = "소속 학과 이름")
    private String majorName;
    @NotNull
    @Schema(description = "좋아요 클릭 여부")
    private Boolean isLike;

    @QueryProjection
    public BoardReadResponse(LocalDateTime regDate, LocalDateTime updateDate, Long postId, String memberNickname,
                             String content, Long clickCount, Long likeCount, Long profId,
                             String profName, String collegeName,String majorName
    ) {
        super(regDate, updateDate);
        this.postId = postId;
        this.memberNickname = memberNickname;
        this.content = content;
        this.clickCount = clickCount;
        this.likeCount = likeCount;
        this.profId = profId;
        this.profName = profName;
        this.collegeName = collegeName;
        this.majorName = majorName;
    }
    public static BoardReadResponse fromEntity(Board board) {
        return BoardReadResponse.builder()
            .regDate(board.getRegDate())
            .updateDate(board.getUpdateDate())
            .postId(board.getPostId())
            .memberNickname(board.getMember().getNickname())
            .content(board.getContent())
            .clickCount(board.getClickCount())
            .likeCount(board.getLikeCount())
            .commentCount((long) board.getComments().size())
            .profId(board.getProfessor().getProfId())
            .profName(board.getProfessor().getProfName())
            .collegeName(board.getProfessor().getCategory().getCollegeName())
            .majorName(board.getProfessor().getCategory().getMajorName())
            .isLike(board.getIsLike())
            .build();
    }
}
