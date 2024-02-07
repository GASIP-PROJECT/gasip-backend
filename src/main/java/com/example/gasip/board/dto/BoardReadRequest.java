package com.example.gasip.board.dto;

import com.example.gasip.board.model.Board;
import com.example.gasip.global.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
//@AllArgsConstructor
@SuperBuilder
public class BoardReadRequest extends BaseTimeEntity {

    private Long postId;

    public static BoardReadRequest toEntity(Board board) {
        return BoardReadRequest.builder()
                .postId(board.getPostId())
                .build();
    }
}
