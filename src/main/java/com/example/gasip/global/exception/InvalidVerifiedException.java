package com.example.gasip.global.exception;

import com.example.gasip.global.constant.ErrorCode;

public class InvalidVerifiedException extends BaseException {
    private final ErrorCode errorCode;
    public InvalidVerifiedException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
