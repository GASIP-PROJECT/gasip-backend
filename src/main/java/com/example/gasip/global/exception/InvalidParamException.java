package com.example.gasip.global.exception;

import com.example.gasip.global.constant.ErrorCode;

public class InvalidParamException extends RuntimeException {

    public InvalidParamException() {
        super(ErrorCode.INVALID_PARAM_ERROR.getMessage());
    }

    public InvalidParamException(String message) {
        super(message);
    }

    public InvalidParamException(String message, Throwable cause) {
        super(message, cause);
    }
}
