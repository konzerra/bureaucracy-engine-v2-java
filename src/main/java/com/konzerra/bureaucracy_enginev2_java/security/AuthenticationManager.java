package com.konzerra.bureaucracy_enginev2_java.security;

import com.konzerra.bureaucracy_enginev2_java.domain.user.User;
import com.konzerra.bureaucracy_enginev2_java.domain.user.UserRepository;
import com.konzerra.bureaucracy_enginev2_java.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {
    private final UserRepository userRepository;
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
        return userRepository.findUserByEmail(principal.getName())
                .filter(User::isEnabled)
                .switchIfEmpty(Mono.error(new UnauthorizedException("user disabled")))
                .map(user -> authentication);
    }
}
