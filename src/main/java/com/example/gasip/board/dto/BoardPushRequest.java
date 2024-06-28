package com.example.gasip.board.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardPushRequest {
    private String targetToken;
    private String title;
    private String body;

    @Builder(toBuilder = true)
    public BoardPushRequest(String targetToken, String title, String body) {
        this.targetToken = targetToken;
        this.title = title;
        this.body = body;
    }
}
