package com.example.gasip.board.dto;

import com.example.gasip.global.entity.BaseTimeEntity;
import com.example.gasip.board.model.Board;
import com.example.gasip.member.model.Member;
import com.example.gasip.professor.model.Professor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 생성 Request DTO 관련 VO")
public class BoardCreateRequest extends BaseTimeEntity {

    @NotNull
    @Schema(description = "게시글 내용")
    private String content;
    @Schema(description = "게시글 조회수")
    private Long clickCount;
    @Schema(description = "게시글 좋아요")
    private Long likeCount;
    @Schema(description = "게시글과 관련된 교수 정보")
    private Professor professor;
    @Schema(description = "게시글 작성한 사용자")
    private Member member;


    public Board toEntity(Professor prof, Member mem) {
        return Board.builder()
                .content(content)
                .clickCount(clickCount)
                .likeCount(likeCount)
                .professor(prof)
                .member(mem)
                .build();
    }

}
