package com.konzerra.bureaucracy_enginev2_java.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class SigninDto {

    @NotNull
    private String email;
    @NotNull
    private String password;
}
