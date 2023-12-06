package com.movie.test.token.service;

import com.movie.test.token.dto.TokenDTO;
import io.jsonwebtoken.Claims;

public interface TokenService {

    String makeJwtToken(TokenDTO tokenDTO);

    Claims readJwtToken(String jwt);
}
