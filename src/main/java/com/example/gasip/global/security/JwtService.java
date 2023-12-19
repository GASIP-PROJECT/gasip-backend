package com.example.gasip.global.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.gasip.global.constant.Role;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class JwtService {

    private static final String SECRET_KEY = "secret_key";
    private static final Duration ACCESS_DURATION = Duration.of(1, ChronoUnit.WEEKS);

    public String issue(Long id, String email, Role role) {
        return JWT.create()
            .withSubject(String.valueOf(id))
            .withClaim("email", email)
            .withClaim("roles", List.of(role.name()))
            .withExpiresAt(Instant.now().plus(ACCESS_DURATION))
            .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public DecodedJWT decode(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
            .build()
            .verify(token);
    }

    public boolean isExpired(DecodedJWT jwt) {
        return jwt.getExpiresAtAsInstant().isBefore(Instant.now());
    }
}
