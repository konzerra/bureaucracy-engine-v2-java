package com.konzerra.bureaucracy_enginev2_java.domain.auth;

import com.konzerra.bureaucracy_enginev2_java.domain.user.User;
import reactor.core.publisher.Mono;

public interface AuthUtil {

    Mono<String> getUserName();

    Mono<Void> isCurrentUser(String email);
}
