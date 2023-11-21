package com.movie.test.token.service;

import com.movie.test.token.dto.tokenDTO;
import io.jsonwebtoken.Claims;

public interface tokenService {

    String makeJwtToken(tokenDTO tokenDTO);

    Claims readJwtToken(String jwt);
}
