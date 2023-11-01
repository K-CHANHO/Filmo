package com.moive.test.login.controller;

import com.google.gson.JsonObject;
import com.moive.test.token.service.tokenServiceImpl;
import com.moive.test.user.dto.userDTO;
import com.moive.test.login.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController {

    @Autowired
    private loginService loginService;

    @Autowired
    private tokenServiceImpl tokenServiceImpl;

    @PostMapping("/login")
    public JsonObject login(String uid, String type){
        userDTO logingUser = userDTO.builder()
                .uid(uid)
                .type(type)
                .build();

        String isExistUser = loginService.isExistUser(logingUser);

        String jwtToken = null;
        if("true".equals(isExistUser)){
            jwtToken = tokenServiceImpl.makeJwtToken(uid, type);
        }

        JsonObject serverData = new JsonObject();
        serverData.addProperty("isExistUser", isExistUser);
        serverData.addProperty("jwt", jwtToken);

        return serverData;
    }
}
