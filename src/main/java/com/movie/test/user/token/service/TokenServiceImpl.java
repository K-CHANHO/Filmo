package com.movie.test.user.token.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.test.user.token.dto.JwtTokenDTO;
import com.movie.test.user.token.dto.TokenDTO;
import com.movie.test.user.token.entity.TokenEntity;
import com.movie.test.user.token.repository.TokenRepository;
import com.movie.test.user.userinfo.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Value("${jwt.secretKey}")
    String secretKey;

    @Value("${jwt.expired.accessToken}")
    Long accessExpiredHours;

    @Value("${jwt.expired.refreshToken}")
    Long refreshExpiredDays;

    @Value("${jwt.issuer}")
    String issuer;

    private ObjectMapper objectMapper = new ObjectMapper();	// JWT를 역직렬화하기 위한 ObjectMapper


    @Override
    public String makeAccessToken(String userId) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .header().add("typ", "jwt").and()
                .issuer(issuer)
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(accessExpiredHours, ChronoUnit.DAYS))) // 30분 -> 개발기간에는 1년
                .claim("userId", userId)
                .claim("type", "access")
                .signWith(key)
                .compact();

    }

    @Override
    public String makeRefreshToken() {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .header().add("typ", "jwt").and()
                .issuer(issuer)
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(refreshExpiredDays, ChronoUnit.DAYS))) // 1일 -> 개발기간에는 1년
                .claim("type", "refresh")
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

    @Override
    public boolean checkUser(TokenDTO tokenDTO) throws JsonProcessingException {

        // 액세스 토큰, 리프레시 토큰 둘 다 필요
        if(tokenDTO.getAccessToken() == null || tokenDTO.getRefreshToken() == null){
            return false;
        }

        // userId 비교
        String userId = decodeJwtPayloadSubject(tokenDTO.getAccessToken());
        TokenEntity tokenEntity = tokenRepository.findByRefreshToken(tokenDTO.getRefreshToken()).orElse(null);
        if(tokenEntity == null || !tokenEntity.getUserId().equals(userId)){
            return false;
        }

        // refreshToken 읽기
        try {
            readJwtToken(tokenDTO.getRefreshToken());
        } catch (Exception e){
            return false;
        }

        return true;
    }

    @Override
    public String resolveUserId(String accessToken) throws JsonProcessingException {
        return decodeJwtPayloadSubject(accessToken);
    }

    @Override
    public TokenEntity saveRefreshToken(JwtTokenDTO tokenDTO) {
        TokenEntity saved = tokenRepository.save(TokenDTO.toEntity(tokenDTO));
        return saved;
    }


    private String decodeJwtPayloadSubject(String oldAccessToken) throws JsonProcessingException {
        return objectMapper.readValue(
                new String(Base64.getDecoder().decode(oldAccessToken.split("\\.")[1]), StandardCharsets.UTF_8), Map.class).get("userId").toString();
    }
}
