package com.example.gasip.global.exception;

import com.example.gasip.global.constant.ErrorCode;

public class CheckFlaggedException extends BaseException {
    private final ErrorCode errorCode;

    public CheckFlaggedException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
