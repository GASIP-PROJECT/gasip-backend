package com.example.gasip.global.exception;

import com.example.gasip.global.constant.ErrorCode;

public class NoStockException extends RuntimeException {

    public NoStockException() {
        super(ErrorCode.NO_STOCK_ERROR.getMessage());
    }

    public NoStockException(String message) {
        super(message);
    }

    public NoStockException(String message, Throwable cause) {
        super(message, cause);
    }

}
