package com.example.gasip.board.dto;

import com.example.gasip.board.model.Board;
import com.example.gasip.common.BaseTimeEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class BoardCreateResponse extends BaseTimeEntity {

    @NotNull
    private Long postId;
    @NotNull
    private String content;
    private Long clickCount;
    private Long likeCount;
    @NotNull
    private Long profId;

    public static BoardCreateResponse fromEntity(Board board) {
        return BoardCreateResponse.builder()
                .regDate(board.getRegDate())
                .updateDate(board.getUpdateDate())
                .postId(board.getPostId())
                .content(board.getContent())
                .clickCount(board.getClickCount())
                .likeCount(board.getLikeCount())
                .profId(board.getProfessor().getProfId())
                .build();
    }


}
