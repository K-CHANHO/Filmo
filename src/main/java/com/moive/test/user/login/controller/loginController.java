package com.moive.test.user.login.controller;

import com.google.gson.JsonObject;
import com.moive.test.user.dto.userDTO;
import com.moive.test.user.login.service.loginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@RestController
public class loginController {

    @Autowired
    private loginService loginService;

    @Value("${jwt.secretKey}")
    String secretKey;

    @Value("${jwt.expiredMs}")
    Long expiredMs;

    @Value("${jwt.issuer}")
    String issuer;

    @PostMapping("/login")
    public JsonObject login(String uid, String type){
        userDTO logingUser = userDTO.builder()
                .uid(uid)
                .type(type)
                .build();

        String isExistUser = loginService.isExistUser(logingUser);

        String jwtToken = null;
        if("true".equals(isExistUser)){
            jwtToken = makeJwtToken(uid);
        }

        JsonObject serverData = new JsonObject();
        serverData.addProperty("isExistUser", isExistUser);
        serverData.addProperty("jwt", jwtToken);

        return serverData;
    }

    private String makeJwtToken(String uid) {

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .header().add("typ", "jwt").and()
                .issuer(issuer)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + expiredMs))
                .claim("uid", uid)
                .signWith(key)
                .compact();
    }
}
