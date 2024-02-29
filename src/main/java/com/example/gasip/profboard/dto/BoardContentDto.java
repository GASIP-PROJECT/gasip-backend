package com.example.gasip.profboard.dto;

import com.example.gasip.profboard.model.ProfBoard;
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

    public static BoardContentDto fromEntity(ProfBoard profBoard) {
        return BoardContentDto.builder()
            .postId(profBoard.getPostId())
            .content(profBoard.getContent())
            .build();
        }
    }

