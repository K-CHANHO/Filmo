package com.movie.test.user.token.service;

import com.movie.test.user.token.dto.JwtTokenDTO;
import com.movie.test.user.userinfo.dto.UserDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenProvider {


    private final Key key;
    public JwtTokenProvider(@Value("${jwt.secretKey}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Value("${jwt.expired.accessToken}")
    private long accessTokenExpiration;
    @Value("${jwt.expired.refreshToken}")
    private long refreshTokenExpiration;

    public JwtTokenDTO createToken(UserDTO userDTO, Authentication authentication){

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("userId", userDTO.getUserId())
                .claim("auth", authorities)
                .setExpiration(Date.from(Instant.now().plus(accessTokenExpiration, ChronoUnit.DAYS)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(Date.from(Instant.now().plus(refreshTokenExpiration, ChronoUnit.DAYS)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JwtTokenDTO.builder()
                .grantType("Bearer ")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(userDTO.getUserId())
                .build();

    }

    public Authentication getAuthentication(String accessToken){

        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();

/*
        if(claims.get("auth") == null){
            throw new RuntimeException("권한 정보가 없습니다");
        }
*/

        List<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UserDetails user = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(user, "", authorities);

    }

    public String getUserId(String accessToken){

        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();


        return claims.get("userId").toString();

    }

    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e){
            log.error("Invalid JWT Token", e);
            return false;
        } catch (ExpiredJwtException e){
            log.error("Expired JWT Token", e);
            return false;
        } catch (UnsupportedJwtException e){
            log.error("Unsupported JWT Token", e);
            return false;
        } catch (IllegalArgumentException e){
            log.error("JWT Claims Is Null", e);
            return false;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }

        return null;
    }
}
