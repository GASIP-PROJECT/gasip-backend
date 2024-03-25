package com.example.gasip.board.dto;

import com.example.gasip.board.model.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardContentDto {

    private Long postId;
    private String content;

    @Builder
    public BoardContentDto(Long postId, String content) {
        this.postId = postId;
        this.content = content;
    }

    public static BoardContentDto fromEntity(Board board) {
        return BoardContentDto.builder()
            .postId(board.getPostId())
            .content(board.getContent())
            .build();
        }
    }

