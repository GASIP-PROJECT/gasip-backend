package com.example.gasip.Board.dto;

import com.example.gasip.Board.basetime.BaseTimeEntity;
import com.example.gasip.Board.model.Board;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@SuperBuilder
public class BoardCreateResponse extends BaseTimeEntity{

    @NotNull
    private Long postId;
    @NotNull
    private String content;
    private Long clickCount;
    private Long likeCount;
    @NotNull
    private Long profId;

//    @Builder
    public BoardCreateResponse(LocalDateTime regDate, LocalDateTime updateDate, Long postId, String content, Long clickCount, Long likeCount, Long profId) {
        super(regDate, updateDate);
        this.postId = postId;
        this.content = content;
        this.clickCount = clickCount;
        this.likeCount = likeCount;
        this.profId = profId;
    }

    //fromEntity메서드 개발
    public static BoardCreateResponse fromEntity(Board board) {
        return BoardCreateResponse.builder()
                .regDate(board.getRegDate())
                .updateDate(board.getUpdateDate())
                .postId(board.getPostId())
                .content(board.getContent())
                .clickCount(board.getClickCount())
                .likeCount(board.getLikeCount())
                .profId(board.getProfId())
                .build();
    }

//    public static BoardCreateResponse fromEntity() {
//        return BoardCreateResponse.builder()
//                .postId(postId)
//                .content(content)
//                .clickCount(clickCount)
//                .likeCount(likeCount)
//                .profId(profId)
//                .build();
//    }


}
