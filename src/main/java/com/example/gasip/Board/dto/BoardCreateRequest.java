package com.example.gasip.Board.dto;

import com.example.gasip.Board.model.BaseTimeEntity;
import com.example.gasip.Board.model.Board;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreateRequest extends BaseTimeEntity {

    @NotNull
    private String content;
    private Long clickCount;
    private Long likeCount;
    @NotNull
    private Long profId;

    @Builder
    public BoardCreateRequest(Long postId, String content, Long clickCount, Long likeCount, LocalDateTime regDate, LocalDateTime updateDate, Long profId) {
        this.content = content;
        this.clickCount = clickCount;
        this.likeCount = likeCount;
        this.profId = profId;
    }
    public Board toEntity() {
        return Board.builder()
                .content(content)
                .clickCount(clickCount)
                .likeCount(likeCount)
                .profId(profId)
                .build();
    }

//    public Board toEntity(BoardCreateRequest boardCreateRequest) {
//        return Board.builder()
//                .content(boardCreateRequest.getContent())
//                .clickCount(boardCreateRequest.getClickCount())
//                .likeCount(boardCreateRequest.getLikeCount())
//                .regDate(boardCreateRequest.getRegDate())
//                .updateDate(boardCreateRequest.getUpdateDate())
//                .profId(boardCreateRequest.getProfId())
//                .build();
//    }

}
