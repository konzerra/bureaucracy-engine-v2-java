package com.konzerra.bureaucracy_enginev2_java.domain.auth.impl;

import com.konzerra.bureaucracy_enginev2_java.domain.auth.AuthUtil;
import com.konzerra.bureaucracy_enginev2_java.exception.ApiException;
import com.konzerra.bureaucracy_enginev2_java.exception.ErrorCode;
import com.konzerra.bureaucracy_enginev2_java.exception.UnauthorizedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

@Component
public class AuthUtilImpl implements AuthUtil {
    @Override
    public Mono<String> getUserName() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .flatMap(authentication -> {
                    if (!(authentication instanceof AnonymousAuthenticationToken)) {
                        return Mono.just(authentication.getName());
                    } else {
                        return Mono.error(new UnauthorizedException("Could not get current authorized user"));
                    }
                });
    }

    @Override
    public Mono<Void> isCurrentUser(String email) {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .flatMap(authentication -> {
                    if (!(authentication instanceof AnonymousAuthenticationToken)) {
                        if(authentication.getName().equals(email)){
                            return Mono.empty();
                        }
                        else{
                            return Mono.error(new ApiException("access denied", ErrorCode.ACCESS_DENIED));
                        }

                    } else {
                        return Mono.error(new UnauthorizedException("Could not get current authorized user"));
                    }
                });
    }
}
