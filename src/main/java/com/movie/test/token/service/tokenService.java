package com.movie.test.token.service;

import io.jsonwebtoken.Claims;

public interface tokenService {

    String makeJwtToken(String uid, String type);

    Claims readJwtToken(String jwt);
}
