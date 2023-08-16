package com.konzerra.bureaucracy_enginev2_java.domain.user;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;

import reactor.core.publisher.Mono;

public interface UserService extends ReactiveUserDetailsService {

    Mono<User> findById(String id);

    Mono<User> update(String id);
}
