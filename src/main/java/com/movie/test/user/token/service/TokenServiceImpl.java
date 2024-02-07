package com.movie.test.user.token.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.secretKey}")
    String secretKey;

    @Value("${jwt.expired.accessToken}")
    Long accessExpiredHours;

    @Value("${jwt.expired.refreshToken}")
    Long refreshExpiredDays;

    @Value("${jwt.issuer}")
    String issuer;


    @Override
    public String makeJwtToken(String userId) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String refreshToken = Jwts.builder()
                .header().add("typ", "jwt").and()
                .issuer(issuer)
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(refreshExpiredDays, ChronoUnit.DAYS))) // 7일
                .claim("userId", userId)
                .signWith(key)
                .compact();


        String accessToken = Jwts.builder()
                .header().add("typ", "jwt").and()
                .issuer(issuer)
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(accessExpiredHours, ChronoUnit.HOURS))) // 1시간
                .claim("userId", userId)
                .signWith(key)
                .compact();

        return accessToken;
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
