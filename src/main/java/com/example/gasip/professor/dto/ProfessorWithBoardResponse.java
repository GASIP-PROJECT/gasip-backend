package com.example.gasip.professor.dto;

import com.example.gasip.board.model.Board;
import com.example.gasip.professor.model.Professor;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ProfessorWithBoardResponse {
    @Schema(description = "교수 ID")
    private Long profId;
    @Schema(description = "교수 이름")
    private String profName;
    @Schema(description = "교수 전공 ID")
    private Long majorId;
    @Schema(description = "교수 전공 이름")
    private String majorName;
    @Schema(description = "교수 평균 평점")
    private String professorAverageGradePoint;
    @Schema(description = "게시글 내용")
    private String content;
    @Schema(description = "게시글 ID")
    private Long postId;
    @Schema(description = "닉네임")
    private String nickname;



    @QueryProjection
    public ProfessorWithBoardResponse(Long profId, String profName, Long majorId, String majorName, String content, Long postId, String nickname) {
        this.profId = profId;
        this.profName = profName;
        this.majorId = majorId;
        this.majorName = majorName;
        this.content = content;
        this.postId = postId;
        this.nickname = nickname;
    }


    public static ProfessorWithBoardResponse fromEntity(Professor professor, Board board) {
        return ProfessorWithBoardResponse.builder()
                .profId(professor.getProfId())
                .profName(professor.getProfName())
                .majorId(professor.getCategory().getId())
                .majorName(professor.getCategory().getMajorName())
                .professorAverageGradePoint(professor.getAverageGradePoint())
                .content(board.getContent())
                .postId(board.getPostId())
                .nickname(board.getMember().getNickname())
                .build();
    }
}
