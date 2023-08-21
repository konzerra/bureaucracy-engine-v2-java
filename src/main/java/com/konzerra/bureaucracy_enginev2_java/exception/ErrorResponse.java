package com.konzerra.bureaucracy_enginev2_java.exception;

public record ErrorResponse(
        String message,
        ErrorCode code
) {
}
