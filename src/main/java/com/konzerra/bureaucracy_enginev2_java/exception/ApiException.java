package com.konzerra.bureaucracy_enginev2_java.exception;

public class ApiException extends RuntimeException{
    protected ErrorCode errorCode;

    public ApiException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
