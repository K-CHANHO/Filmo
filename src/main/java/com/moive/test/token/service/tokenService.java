package com.moive.test.token.service;

public interface tokenService {

    String makeJwtToken(String uid, String type);
}
