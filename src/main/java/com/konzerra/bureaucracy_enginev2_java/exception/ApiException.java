package com.konzerra.bureaucracy_enginev2_java.exception;

import lombok.Getter;

public class ApiException extends RuntimeException{

    @Getter
    protected ErrorCode errorCode;

    public ApiException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
