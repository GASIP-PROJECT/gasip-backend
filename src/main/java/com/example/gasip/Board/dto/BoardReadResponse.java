package com.example.gasip.Board.dto;

import com.example.gasip.Board.basetime.BaseTimeEntity;
import com.example.gasip.Board.model.Board;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@SuperBuilder
public class BoardReadResponse extends BaseTimeEntity {
    @NotNull
    private Long postId;
    @NotNull
    private String content;
    private Long clickCount;
    private Long likeCount;
//    @NotNull
//    private LocalDateTime regDate;
//    @NotNull
//    private LocalDateTime updateDate;
    @NotNull
    private Long profId;

//    @Builder
    public BoardReadResponse(LocalDateTime regDate, LocalDateTime updateDate, Long postId, String content, Long clickCount, Long likeCount, Long profId) {
        super(regDate, updateDate);
        this.postId = postId;
        this.content = content;
        this.clickCount = clickCount;
        this.likeCount = likeCount;
        this.profId = profId;
    }
    public static BoardReadResponse fromEntity(Board board) {
        return BoardReadResponse.builder()
                .regDate(board.getRegDate())
                .updateDate(board.getUpdateDate())
                .postId(board.getPostId())
                .content(board.getContent())
                .clickCount(board.getClickCount())
                .likeCount(board.getLikeCount())
                .profId(board.getProfId())
                .build();
    }
}
