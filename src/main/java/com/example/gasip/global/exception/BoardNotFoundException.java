package com.example.gasip.global.exception;

import com.example.gasip.global.constant.ErrorCode;
import lombok.Getter;

@Getter
public class BoardNotFoundException extends BaseException{
    private final ErrorCode errorCode;
    public BoardNotFoundException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

}
