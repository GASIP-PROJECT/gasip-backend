package com.example.gasip.global.exception;

import com.example.gasip.global.constant.ErrorCode;

public class SelfBoardReportException extends BaseException {
    private final ErrorCode errorCode;
    public SelfBoardReportException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
