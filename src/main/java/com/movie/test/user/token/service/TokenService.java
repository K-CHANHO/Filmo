package com.movie.test.user.token.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.movie.test.user.token.dto.TokenDTO;
import io.jsonwebtoken.Claims;

public interface TokenService {

    String makeAccessToken(String userId);
    String makeRefreshToken();
    Claims readJwtToken(String jwt);
    String resolveToken(String token);

    boolean checkUser(TokenDTO tokenDTO) throws JsonProcessingException;
}
