package com.example.gasip.global.exception.handler;

import com.example.gasip.global.constant.ErrorCode;

public class DuplicateReportException extends RuntimeException {
    private ErrorCode errorCode;
    public DuplicateReportException(ErrorCode errorCode) {
        super(ErrorCode.DUPLICATE_REPORT.getMessage());
        this.errorCode = errorCode;
    }
}
