package com.example.gasip.grade.dto.request;

import com.example.gasip.board.model.Board;
import com.example.gasip.grade.model.Grade;
import com.example.gasip.member.model.Member;
import com.example.gasip.professor.model.Professor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "평점 생성 Request DTO")
public class GradeCreateRequest {
    @Schema(description = "입력 평점")
    private int gradepoint;
    @Schema(description = "평점 등록자")
    private Member member;

    public Grade toEntity(Professor professor,Member member) {
        return Grade.builder()
            .gradepoint(gradepoint)
            .professor(professor)
            .member(member)
            .build();
    }

}
