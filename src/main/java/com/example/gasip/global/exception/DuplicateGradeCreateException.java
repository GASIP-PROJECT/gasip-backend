package com.example.gasip.global.exception;

import com.example.gasip.global.constant.ErrorCode;

public class DuplicateGradeCreateException extends BaseException {
    private final ErrorCode errorCode;
    public DuplicateGradeCreateException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
