package com.example.gasip.profboard.dto;

import com.example.gasip.profboard.model.ProfBoard;
import com.example.gasip.global.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
//@AllArgsConstructor
@SuperBuilder
public class BoardReadRequest extends BaseTimeEntity {

    private Long postId;

    public static BoardReadRequest toEntity(ProfBoard profBoard) {
        return BoardReadRequest.builder()
                .postId(profBoard.getPostId())
                .build();
    }
}
