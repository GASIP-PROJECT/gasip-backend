package com.example.gasip.global.exception.handler;

import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.global.constant.ErrorCode;
import com.example.gasip.global.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final MessageSource messageSource;

    @ExceptionHandler({
        NoSuchEntityException.class,
        DuplicateEmailException.class,
        InvalidParamException.class,
        NoStockException.class,
        MemberNotFoundException.class,
        DuplicatePhoneNumberException.class,
        DuplicateResourceException.class
    })
    public ResponseEntity<?> handleCustomException(
        RuntimeException ex
    ) {
        log.error("handling {}, message : {}", ex.getClass().toString(), ex.getMessage());

        return ResponseEntity
            .badRequest()
            .body(
                ApiUtils.error(ex.getMessage(),HttpStatus.BAD_REQUEST)
            );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(
        AuthenticationException ex
    ) {
        log.error("handling {}, message : {}", ex.getClass().toString(), ex.getMessage());

        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(
                ApiUtils.error(ErrorCode.UNAUTHORIZED.getMessage(),HttpStatus.UNAUTHORIZED)
            );
    }

    @ExceptionHandler(AccessForbiddenException.class)
    public ResponseEntity<?> handleAccessForbidden(
        AccessForbiddenException ex
    ) {
        log.error("handling {}, message : {}", ex.getClass().toString(), ex.getMessage());

        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(
                ApiUtils.error(ex.getMessage(),HttpStatus.FORBIDDEN)
            );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(
        HttpMessageNotReadableException ex
    ) {
        log.error("handling {}, message : {}", ex.getClass().toString(), ex.getMessage());

        return ResponseEntity
            .badRequest()
            .body(
                ApiUtils.error(ErrorCode.PARAM_PARSING_MAPPING_ERROR.getMessage(),HttpStatus.BAD_REQUEST)
            );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(
        MethodArgumentTypeMismatchException ex
    ) {
        log.error("handling {}, message : {}", ex.getClass().toString(), ex.getMessage());

        return ResponseEntity
            .badRequest()
            .body(
                ApiUtils.error(ErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH.getMessage(),HttpStatus.BAD_REQUEST)
            );
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNoHandlerFoundException(
        NoHandlerFoundException ex
    ) {
        log.error("handling {}, message : {}", ex.getClass().toString(), ex.getMessage());

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                ApiUtils.error(ErrorCode.NO_HANDLER_FOUND.getMessage(),HttpStatus.NOT_FOUND)
            );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(
        HttpRequestMethodNotSupportedException ex
    ) {
        log.error("handling {}, message : {}", ex.getClass().toString(), ex.getMessage());

        return ResponseEntity
            .status(HttpStatus.METHOD_NOT_ALLOWED)
            .body(
                ApiUtils.error(ErrorCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED.getMessage(),HttpStatus.METHOD_NOT_ALLOWED)
            );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(
        Exception ex
    ) {
        log.error("handling {}, message : {}", ex.getClass().toString(), ex.getMessage());

        return ResponseEntity
            .internalServerError()
            .body(
                ApiUtils.error(ErrorCode.INTERNAL_SERVER_ERROR.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR)
            );
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?> duplicateResourceException(
            DuplicateResourceException ex)
     {
            log.error("handling {}, message : {}", ex.getClass(), ex.getMessage());

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(
                            ApiUtils.error(ErrorCode.DUPLICATE_LIKE.getMessage(), ErrorCode.DUPLICATE_LIKE.getHttpStatus())
                    );
        }
}

