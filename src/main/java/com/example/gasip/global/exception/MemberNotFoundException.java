package com.example.gasip.global.exception;

import com.example.gasip.global.constant.ErrorCode;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException() {
        super(ErrorCode.NOT_FOUND_MEMBER.getMessage());
    }

    public MemberNotFoundException(String message) {
        super(message);
    }

    public MemberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
