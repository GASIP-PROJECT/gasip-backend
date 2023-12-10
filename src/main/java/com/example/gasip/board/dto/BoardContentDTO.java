package com.example.gasip.board.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardContentDTO {

    private Long postId;
    private String content;

    public BoardContentDTO(Long postId, String content) {
        this.postId = postId;
        this.content = content;
    }
}
