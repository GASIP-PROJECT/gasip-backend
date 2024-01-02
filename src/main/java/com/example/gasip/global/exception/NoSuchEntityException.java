package com.example.gasip.global.exception;


import com.example.gasip.global.constant.ErrorCode;

public class NoSuchEntityException extends RuntimeException {

    public NoSuchEntityException() {
        super(ErrorCode.NO_SUCH_ENTITY.getMessage());
    }

    public NoSuchEntityException(String message) {
        super(message);
    }

    public NoSuchEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
