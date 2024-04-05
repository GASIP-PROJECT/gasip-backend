package com.example.gasip.global.exception;

import com.example.gasip.global.constant.ErrorCode;

public class InvalidGradePointException extends BaseException {
    private final ErrorCode errorCode;
    public InvalidGradePointException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
