package com.example.gasip.board.dto;

import com.example.gasip.common.BaseTimeEntity;
import com.example.gasip.board.model.Board;
import com.example.gasip.professor.model.Professor;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Professor professor;

    public BoardReadResponse(LocalDateTime regDate, LocalDateTime updateDate, Long postId, String content, Long clickCount, Long likeCount, Professor professor) {
        super(regDate, updateDate);
        this.postId = postId;
        this.content = content;
        this.clickCount = clickCount;
        this.likeCount = likeCount;
//        this.professor = professor;
    }
    public static BoardReadResponse fromEntity(Board board) {
        return BoardReadResponse.builder()
                .regDate(board.getRegDate())
                .updateDate(board.getUpdateDate())
                .postId(board.getPostId())
                .content(board.getContent())
                .clickCount(board.getClickCount())
                .likeCount(board.getLikeCount())
//                .professor(board.getProfessor())
                .build();
    }
}
