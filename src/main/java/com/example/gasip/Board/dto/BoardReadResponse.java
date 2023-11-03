package com.example.gasip.Board.dto;

import com.example.gasip.Board.model.BaseTimeEntity;
import com.example.gasip.Board.model.Board;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardReadResponse extends BaseTimeEntity {
    @NotNull
    private Long postId;
    @NotNull
    private String content;
    private Long clickCount;
    private Long likeCount;
    @NotNull
    private Long profId;

    @Builder
    public BoardReadResponse(Long postId, String content, Long clickCount, Long likeCount, Long profId) {
        this.postId = postId;
        this.content = content;
        this.clickCount = clickCount;
        this.likeCount = likeCount;
        this.profId = profId;
    }

    public static BoardReadResponse fromEntity(Board board) {
        return BoardReadResponse.builder()
                .postId(board.getPostId())
                .content(board.getContent())
                .clickCount(board.getClickCount())
                .likeCount(board.getLikeCount())
                .profId(board.getProfId())
                .build();
    }
}
