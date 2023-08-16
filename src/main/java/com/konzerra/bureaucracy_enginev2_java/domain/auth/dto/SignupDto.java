package com.konzerra.bureaucracy_enginev2_java.domain.auth.dto;

public record SignupDto(
        String email,
        String name,
        String password

) {
}
