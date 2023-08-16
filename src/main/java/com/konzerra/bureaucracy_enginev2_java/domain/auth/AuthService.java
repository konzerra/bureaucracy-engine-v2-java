package com.konzerra.bureaucracy_enginev2_java.domain.auth;

import com.konzerra.bureaucracy_enginev2_java.domain.auth.dto.SigninDto;
import com.konzerra.bureaucracy_enginev2_java.domain.auth.dto.SignupDto;
import com.konzerra.bureaucracy_enginev2_java.domain.auth.jwt.JwtDetails;
import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<JwtDetails> signin(SigninDto signinDto);

    Mono<JwtDetails> signup(SignupDto signupDto);
}
