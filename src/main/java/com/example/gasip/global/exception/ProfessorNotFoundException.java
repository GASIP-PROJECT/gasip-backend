package com.example.gasip.global.exception;

import com.example.gasip.global.constant.ErrorCode;

public class ProfessorNotFoundException extends BaseException {
    private final ErrorCode errorCode;
    public ProfessorNotFoundException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
