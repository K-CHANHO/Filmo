package com.movie.test.user.token.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.movie.test.user.token.dto.JwtTokenDTO;
import com.movie.test.user.token.dto.TokenDTO;
import com.movie.test.user.token.entity.TokenEntity;
import io.jsonwebtoken.Claims;

public interface TokenService {

    String makeAccessToken(String userId);
    String makeRefreshToken();
    Claims readJwtToken(String jwt);
    String resolveToken(String token);

    boolean checkUser(TokenDTO tokenDTO) throws JsonProcessingException;
    String resolveUserId(String accessToken) throws JsonProcessingException;

    TokenEntity saveRefreshToken(JwtTokenDTO tokenDTO);
}
