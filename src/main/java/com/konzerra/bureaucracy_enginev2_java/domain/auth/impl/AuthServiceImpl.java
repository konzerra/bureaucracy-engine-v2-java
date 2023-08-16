package com.konzerra.bureaucracy_enginev2_java.domain.auth.impl;

import com.konzerra.bureaucracy_enginev2_java.domain.auth.AuthService;
import com.konzerra.bureaucracy_enginev2_java.domain.auth.dto.SigninDto;
import com.konzerra.bureaucracy_enginev2_java.domain.auth.dto.SignupDto;
import com.konzerra.bureaucracy_enginev2_java.domain.auth.jwt.JwtDetails;
import com.konzerra.bureaucracy_enginev2_java.domain.user.User;
import com.konzerra.bureaucracy_enginev2_java.domain.user.UserRepository;
import com.konzerra.bureaucracy_enginev2_java.domain.user.role.Role;
import com.konzerra.bureaucracy_enginev2_java.domain.user.role.RoleNames;
import com.konzerra.bureaucracy_enginev2_java.domain.user.role.RoleRepository;
import com.konzerra.bureaucracy_enginev2_java.exception.AuthException;
import com.konzerra.bureaucracy_enginev2_java.exception.ErrorCode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.duration}")
    private Long duration;
    @Value("${jwt.issuer}")
    private String issuer;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    private JwtDetails generateToken(User user) {
        Map<String, Object> claims = new HashMap<>() {{
            ArrayList<String> roles = new ArrayList<>(Collections.emptyList());
            user.getRoles().forEach(role -> {
                roles.add(role.getName());
            });
            put("roles", roles);
            put("username", user.getEmail());
        }};
        return generateToken(claims, user.getEmail());
    }

    private JwtDetails generateToken(Map<String, Object> claims, String subject) {
        long expirationTimeInMillis = duration * 1000L;
        Date expirationDate = new Date(new Date().getTime() + expirationTimeInMillis);

        return generateToken(expirationDate, claims, subject);
    }

    private JwtDetails generateToken(Date expirationDate, Map<String, Object> claims, String subject) {
        Date createdDate = new Date();
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(issuer)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setId(UUID.randomUUID().toString())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secret.getBytes()))
                .compact();

        return JwtDetails.builder()
                .token(token)
                .issuedAt(createdDate)
                .expiresAt(expirationDate)
                .build();
    }

    @Override
    public Mono<JwtDetails> signin(SigninDto signinDto) {

        return userRepository.findUserByEmail(signinDto.getEmail())
                .flatMap(user -> {
                    if(!user.getEnabled()){
                        return Mono.error(new AuthException("Contact support", ErrorCode.ACCOUNT_DISABLED));
                    }
                    if(!passwordEncoder.matches(signinDto.getPassword(), user.getPassword())){
                        return Mono.error(new AuthException("invalid credentials", ErrorCode.ACCOUNT_INVALID_CREDENTIALS));
                    }

                    return Mono.just(generateToken(user).toBuilder()
                                    .userId(user.getId())
                                    .email(user.getEmail())
                                    .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                                .build());

                })
                .switchIfEmpty(Mono.error(new AuthException("invalid credentials", ErrorCode.ACCOUNT_INVALID_CREDENTIALS)));
    }

    @Override
    @Transactional
    public Mono<JwtDetails> signup(SignupDto signupDto) {
        return roleRepository.findByName(RoleNames.ADMIN.getName())
                .flatMap(role -> {
                    User user = User.builder()
                            .name(signupDto.name())
                            .email(signupDto.email())
                            .password(passwordEncoder.encode(signupDto.password()))
                            .roles(Collections.singletonList(role))
                            .build();

                    return userRepository.save(user)
                            .flatMap(_user -> {
                                return signin(new SigninDto(signupDto.email(), signupDto.password()));
                            });
                })
                .switchIfEmpty(
                        Mono.error(new RuntimeException())
                );
    }
}
