package com.example.gasip.global.exception;

import com.example.gasip.global.constant.ErrorCode;

public class DuplicateMemberException extends BaseException {
    private final ErrorCode errorCode;
    public DuplicateMemberException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
