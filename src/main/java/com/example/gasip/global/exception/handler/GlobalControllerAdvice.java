package com.example.gasip.global.exception.handler;

import com.example.gasip.global.api.ApiUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final MessageSource messageSource;

    @ExceptionHandler({
//        NoSuchEntityException.class,
//        DuplicateEmailException.class,
//        InvalidParamException.class,
//        NoStockException.class,
//        MemberNotFoundException.class,
//        DuplicatePhoneNumberException.class
    })
    public ResponseEntity<?> handleCustomException(
        RuntimeException ex
    ) {
        log.error("handling {}, message : {}", ex.getClass().toString(), ex.getMessage());

        return ResponseEntity
            .badRequest()
            .body(
                ApiUtils.error(ex.getMessage(), HttpStatus.BAD_REQUEST)
            );
    }

}
