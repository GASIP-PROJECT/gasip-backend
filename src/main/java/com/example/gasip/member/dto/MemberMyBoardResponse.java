package com.example.gasip.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MemberMyBoardResponse {
    List<ArrayList<?>> boards;

    public static MemberMyBoardResponse fromEntity(List<ArrayList<?>> boards) {
        return MemberMyBoardResponse.builder()
            .boards(boards)
            .build();
    }
}
