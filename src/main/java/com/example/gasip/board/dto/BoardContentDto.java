package com.example.gasip.board.dto;

import com.example.gasip.board.model.Board;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardContentDto {
    @Schema(description = "게시글 Id", example = "1")
    private Long postId;
    @Schema(description = "게시글 내용", example = "ㅅㅇㅊ교수님 수업 과제 뭔가요?")
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

