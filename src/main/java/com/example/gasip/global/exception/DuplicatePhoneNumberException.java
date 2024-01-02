package com.example.gasip.global.exception;


import com.example.gasip.global.constant.ErrorCode;

public class DuplicatePhoneNumberException extends RuntimeException {
    public DuplicatePhoneNumberException() {
        super(ErrorCode.DUPLICATE_PHONENUMBER.getMessage());
    }

    public DuplicatePhoneNumberException(String message) {
        super(message);
    }

    public DuplicatePhoneNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
