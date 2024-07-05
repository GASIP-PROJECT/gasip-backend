package com.example.gasip.global.exception;

import com.example.gasip.global.constant.ErrorCode;

public class InvalidWritterException extends BaseException {
    private final ErrorCode errorCode;
    public InvalidWritterException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
