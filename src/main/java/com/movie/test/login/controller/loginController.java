package com.movie.test.login.controller;

import com.google.gson.JsonObject;
import com.movie.test.token.dto.tokenDTO;
import com.movie.test.token.service.tokenServiceImpl;
import com.movie.test.user.dto.userDTO;
import com.movie.test.login.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController {

    @Autowired
    private loginService loginService;

    @Autowired
    private tokenServiceImpl tokenServiceImpl;

    /**
     * @summary 로그인 요청
     * @param logingUser
     * @return (회원인경우) status : 200 및 jwt, USERINFO
     * @return (비회원인경우) status : 406 - 회원가입 페이지로 이동
     */
    @PostMapping("/login")
    public ResponseEntity login(userDTO logingUser){

        String jwtToken = null;
        userDTO isExistUser = null;
        JsonObject serverData = new JsonObject();

        isExistUser = loginService.isExistUser(logingUser);
        if(isExistUser != null){
            tokenDTO token = tokenDTO.builder()
                    .uid(isExistUser.getUid())
                    .type(isExistUser.getType())
                    .userId(isExistUser.getUserId())
                    .build();

            jwtToken = tokenServiceImpl.makeJwtToken(token);
            serverData.addProperty("status", "200");
            serverData.addProperty("jwt", jwtToken);
            return new ResponseEntity<>(serverData, HttpStatus.OK);
        } else {
            serverData.addProperty("status", "406");
            return new ResponseEntity<>(serverData, HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
