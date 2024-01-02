package com.example.gasip.global.exception;


import com.example.gasip.global.constant.ErrorCode;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException() {
        super(ErrorCode.DUPLICATE_EMAIL.getMessage());
    }

    public DuplicateEmailException(String message) {
        super(message);
    }

    public DuplicateEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
