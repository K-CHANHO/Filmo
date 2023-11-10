package com.movie.test.token.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class tokenServiceImpl implements tokenService {

    @Value("${jwt.secretKey}")
    String secretKey;

    @Value("${jwt.expiredMs}")
    Long expiredMs;

    @Value("${jwt.issuer}")
    String issuer;

    public String makeJwtToken(String uid, String type) {

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .header().add("typ", "jwt").and()
                .issuer(issuer)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + expiredMs))
                .claim("uid", uid)
                .claim("type", type)
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


}
