package com.konzerra.bureaucracy_enginev2_java.domain.auth.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class JwtDetails {
    private String userId;
    private String email;
    private String token;
    private Date issuedAt;
    private Date expiresAt;
    private List<String> roles;
}
