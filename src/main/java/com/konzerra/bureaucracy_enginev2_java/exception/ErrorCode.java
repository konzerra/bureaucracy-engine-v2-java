package com.konzerra.bureaucracy_enginev2_java.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    ACCOUNT_DISABLED("ACCOUNT_DISABLED"),
    ACCOUNT_INVALID_CREDENTIALS("ACCOUNT_INVALID_CREDENTIALS"),
    UNAUTHORIZED("UNAUTHORIZED");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

}
