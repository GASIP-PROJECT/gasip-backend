package com.example.gasip.Board.dto;

import com.example.gasip.Board.basetime.BaseTimeEntity;
import com.example.gasip.Board.model.Board;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

//    @Builder
    public BoardCreateRequest(Long postId, String content, Long clickCount, Long likeCount, Long profId) {
        this.content = content;
        this.clickCount = clickCount;
        this.likeCount = likeCount;
        this.profId = profId;
    }

    public Board toEntity(BoardCreateRequest boardCreateRequest) {
        return Board.builder()
                .content(boardCreateRequest.getContent())
                .clickCount(boardCreateRequest.getClickCount())
                .likeCount(boardCreateRequest.getLikeCount())
                .profId(boardCreateRequest.getProfId())
                .build();
    }
//    public Board toEntity() {
//        return Board.builder()
//                .content(content)
//                .clickCount(clickCount)
//                .likeCount(likeCount)
//                .profId(profId)
//                .build();
//    }

}
