package com.example.gasip.freefeed.dto;

import com.example.gasip.board.model.Board;
import com.example.gasip.freefeed.model.Freefeed;
import com.example.gasip.member.model.Member;
import com.example.gasip.professor.model.Professor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FeedCreateRequest {
    @NotNull
    @Schema(description = "게시글 내용")
    private String feed;
    @Schema(description = "게시글 조회수")
    private Long clickCount;
    @Schema(description = "게시글 좋아요")
    private Long likeCount;
    @Schema(description = "게시글 작성한 사용자")
    private Member member;


    public Freefeed toEntity(Member mem) {
        return Freefeed.builder()
            .feed(feed)
            .clickCount(clickCount)
            .likeCount(likeCount)
            .member(mem)
            .build();
    }
}
