package com.konzerra.bureaucracy_enginev2_java.domain.auth;


import com.konzerra.bureaucracy_enginev2_java.domain.auth.dto.SigninDto;
import com.konzerra.bureaucracy_enginev2_java.domain.auth.dto.SignupDto;
import com.konzerra.bureaucracy_enginev2_java.domain.auth.jwt.JwtDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/api/v1/auth/signin")
    public Mono<JwtDetails> signin(
            @RequestBody SigninDto signinDto
    ) {
        return authService.signin(signinDto);
    }
    @PostMapping("/api/v1/auth/signup")
    public Mono<JwtDetails> signup(
            @RequestBody SignupDto signupDto
    ) {
        System.out.println("In controller to register user");
        return authService.signup(signupDto);
    }

}
