package com.example.gasip.Board.dto;

import com.example.gasip.Board.model.Board;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreateRequest {

    @NotNull
    private String content;
    private Long clickCount;
    private Long likeCount;
//    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime regDate;
//    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime updateDate;
    @NotNull
    private Long profId;

    @Builder
    public BoardCreateRequest(Long postId, String content, Long clickCount, Long likeCount, LocalDateTime regDate, LocalDateTime updateDate, Long profId) {
        this.content = content;
        this.clickCount = clickCount;
        this.likeCount = likeCount;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.profId = profId;
    }
    public Board toEntity() {
        return Board.builder()
                .content(content)
                .clickCount(clickCount)
                .likeCount(likeCount)
                .regDate(regDate)
                .updateDate(updateDate)
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
