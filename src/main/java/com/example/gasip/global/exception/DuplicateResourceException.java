package com.example.gasip.global.exception;

import com.example.gasip.global.constant.ErrorCode;


public class DuplicateResourceException extends RuntimeException{
    private ErrorCode errorCode;
    public DuplicateResourceException(ErrorCode errorCode) {
        super(ErrorCode.DUPLICATE_LIKE.getMessage());
        this.errorCode = errorCode;
    }

//    public DuplicateResourceException(String message, Throwable cause) {
//        super(message, cause);
//    }

}
