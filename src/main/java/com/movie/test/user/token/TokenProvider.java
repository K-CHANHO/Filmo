package com.movie.test.user.token;

import com.movie.test.user.userinfo.dto.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class TokenProvider {

    @Value("${jwt.secretKey}")
    String secretKey;

    @Value("${jwt.expiredMs}")
    Long accessTokenExpiration;

    @Value("${jwt.issuer}")
    String issuer;

    private final SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    public String createToken(UserDTO userDTO){
        return Jwts.builder()
                .claim("userId", userDTO.getUserId())
                .expiration(new Date(new Date().getTime() + accessTokenExpiration))
                .signWith(key)
                .compact();
    }


}
