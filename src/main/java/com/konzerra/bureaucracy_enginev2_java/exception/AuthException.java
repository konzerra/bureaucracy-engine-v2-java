package com.konzerra.bureaucracy_enginev2_java.exception;

public class AuthException extends ApiException{
    public AuthException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
