package com.konzerra.bureaucracy_enginev2_java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleAuthException(AuthException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ErrorCode.ACCOUNT_INVALID_CREDENTIALS);
        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleBadRequestException(BadRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ErrorCode.ACCOUNT_INVALID_CREDENTIALS);
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse));
    }
}
