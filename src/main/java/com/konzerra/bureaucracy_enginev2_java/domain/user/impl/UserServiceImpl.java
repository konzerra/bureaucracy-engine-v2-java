package com.konzerra.bureaucracy_enginev2_java.domain.user.impl;

import com.konzerra.bureaucracy_enginev2_java.domain.user.User;
import com.konzerra.bureaucracy_enginev2_java.domain.user.UserRepository;
import com.konzerra.bureaucracy_enginev2_java.domain.user.UserService;
import com.konzerra.bureaucracy_enginev2_java.domain.user.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<User> update(String id) {

        return null;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        System.out.println("Find by username");
        return userRepository.findUserByEmail(username)
                .map(this::toUserDetails)
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")));
    }

    private UserDetails toUserDetails(User user){
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .authorities(mapRolesToAuthorities(user.getRoles()))
                .build();
    }

    private List<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .toList();

        return new ArrayList<>(authorities);
    }
}
