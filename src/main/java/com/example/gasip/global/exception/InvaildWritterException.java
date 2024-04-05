package com.example.gasip.global.exception;

import com.example.gasip.global.constant.ErrorCode;

public class InvaildWritterException extends BaseException {
    private final ErrorCode errorCode;
    public InvaildWritterException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
