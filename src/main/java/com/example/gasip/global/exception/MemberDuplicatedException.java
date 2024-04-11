package com.example.gasip.global.exception;

import com.example.gasip.global.constant.ErrorCode;

public class MemberDuplicatedException extends BaseException {
    private final ErrorCode errorCode;
    public MemberDuplicatedException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
