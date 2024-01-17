package com.movie.test.user.token.service;

import io.jsonwebtoken.Claims;

public interface TokenService {

    String makeJwtToken(String userId);

    Claims readJwtToken(String jwt);

    String resolveToken(String token);
}
