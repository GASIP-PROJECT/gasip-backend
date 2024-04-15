package com.example.gasip.board.dto;

import com.example.gasip.board.model.Board;
import com.example.gasip.comment.dto.CommentReadResponse;
import com.example.gasip.global.entity.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class BoardReadAllInfoResponse extends BaseTimeEntity {
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
    @Schema(description = "댓글 개수")
    private Long numberOfComment;
    @Schema(description = "댓글 리스트")
    private List<CommentReadResponse> comments;

    public BoardReadAllInfoResponse(LocalDateTime regDate, LocalDateTime updateDate, Long postId, String content,
                                    Long clickCount, Long likeCount, Long profId, int gradePoint, String profName,
                                    String collegeName, String majorName, List<CommentReadResponse> comments,
                                    Long numberOfComment
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
        this.numberOfComment = numberOfComment;
        this.comments = comments;
    }
    public static BoardReadAllInfoResponse fromEntity(Board board,List<CommentReadResponse> commentList) {
        return BoardReadAllInfoResponse.builder()
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
            .numberOfComment(Long.valueOf(commentList.size()))
            .comments(commentList)
            .build();
    }
}
