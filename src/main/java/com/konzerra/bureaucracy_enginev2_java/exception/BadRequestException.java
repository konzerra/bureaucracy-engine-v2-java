package com.konzerra.bureaucracy_enginev2_java.exception;

public class BadRequestException extends ApiException{
    public BadRequestException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
