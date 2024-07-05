package com.example.gasip.global.exception;


import com.example.gasip.global.constant.ErrorCode;

public class NoSuchVerifiedCodeException extends BaseException {
    private final ErrorCode errorCode;
    public NoSuchVerifiedCodeException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
