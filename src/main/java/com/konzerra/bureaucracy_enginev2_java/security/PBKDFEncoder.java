package com.konzerra.bureaucracy_enginev2_java.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Component
public class PBKDFEncoder implements PasswordEncoder {

    @Value("${jwt.password.encoder.secret}")
    private String secret;

    @Value("${jwt.password.encoder.iteration}")
    private Integer iteration;

    @Value("${jwt.password.encoder.key_length}")
    private Integer keyLength;

    private static final String KEY_INSTANCE = "PBKDF2WithHmacSHA512";
    @Override
    public String encode(CharSequence rawPassword) {
        try {
            byte[] result = SecretKeyFactory.getInstance(KEY_INSTANCE)
                    .generateSecret(
                            new PBEKeySpec(rawPassword.toString().toCharArray(),
                                    secret.getBytes(),iteration, keyLength )
                    ).getEncoded();
            return Base64.getEncoder().encodeToString(result);
        }catch (NoSuchAlgorithmException | InvalidKeySpecException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println("Comaparing passwords");
        return encode(rawPassword).equals(encodedPassword);
    }
}
