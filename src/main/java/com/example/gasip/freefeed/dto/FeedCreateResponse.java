package com.example.gasip.freefeed.dto;

import com.example.gasip.freefeed.model.Freefeed;
import com.example.gasip.global.entity.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class FeedCreateResponse extends BaseTimeEntity {
    @NotNull
    private Long feedId;
    @NotNull
    @Schema(description = "게시글 내용")
    private String feed;
    @Schema(description = "게시글 조회수")
    private Long clickCount;
    @Schema(description = "게시글 좋아요")
    private Long likeCount;

    //fromEntity메서드 개발
    public static FeedCreateResponse fromEntity(Freefeed feed) {
        return FeedCreateResponse.builder()
            .regDate(feed.getRegDate())
            .updateDate(feed.getUpdateDate())
            .feedId(feed.getFeedId())
            .feed(feed.getFeed())
            .clickCount(0L)
            .likeCount(0L)
            .build();
    }
}
