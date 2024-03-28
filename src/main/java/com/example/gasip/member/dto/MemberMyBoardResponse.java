package com.example.gasip.member.dto;

import com.example.gasip.board.dto.BoardContentDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MemberMyBoardResponse {
    @Schema(description = "사용자가 작성한 게시글 목록", implementation = BoardContentDto.class)
    List<ArrayList<?>> boards;

    public static MemberMyBoardResponse fromEntity(List<ArrayList<?>> boards) {
        return MemberMyBoardResponse.builder()
            .boards(boards)
            .build();
    }
}
