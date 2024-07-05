package com.example.gasip.global.exception;

import com.example.gasip.global.constant.ErrorCode;

public class DuplicateNickNameException extends BaseException {
    private final ErrorCode errorCode;
    public DuplicateNickNameException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
