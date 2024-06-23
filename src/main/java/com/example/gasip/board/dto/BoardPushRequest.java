package com.example.gasip.board.dto;

import lombok.Getter;

@Getter
public class BoardPushRequest {
    private String targetToken;
    private String title;
    private String body;
}
