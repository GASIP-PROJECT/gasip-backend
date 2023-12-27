package com.example.gasip.exception;

public class BoardNotFoundException extends RuntimeException{
    public BoardNotFoundException() {
    }
    public BoardNotFoundException(String message) {
        super(message);
    }

    public BoardNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
