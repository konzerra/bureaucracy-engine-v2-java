package com.konzerra.bureaucracy_enginev2_java.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    ACCOUNT_DISABLED("ACCOUNT_DISABLED"),
    ACCOUNT_INVALID_CREDENTIALS("ACCOUNT_INVALID_CREDENTIALS"),
    ACCESS_DENIED("ACCESS_DENIED"),
    UPDATE_FAILED("UPDATE_FAILED"),
    UNAUTHORIZED("UNAUTHORIZED");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

}
