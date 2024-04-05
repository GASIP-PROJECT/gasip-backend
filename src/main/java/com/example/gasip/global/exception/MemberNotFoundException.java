package com.example.gasip.global.exception;

import com.example.gasip.global.constant.ErrorCode;

public class MemberNotFoundException extends BaseException {
    private final ErrorCode errorCode;
    public MemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
