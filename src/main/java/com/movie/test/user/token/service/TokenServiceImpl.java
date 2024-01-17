package com.movie.test.user.token.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.secretKey}")
    String secretKey;

    @Value("${jwt.expiredMs}")
    Long expiredMs;

    @Value("${jwt.issuer}")
    String issuer;


    @Override
    public String makeJwtToken(String userId) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .header().add("typ", "jwt").and()
                .issuer(issuer)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .claim("userId", userId)
                .signWith(key)
                .compact();
    }

    @Override
    public Claims readJwtToken(String jwt) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    @Override
    public String resolveToken(String token) {

        if(StringUtils.hasText(token) && token.startsWith("Bearer")){
            return token.substring(7);
        }

        return null;
    }
}
