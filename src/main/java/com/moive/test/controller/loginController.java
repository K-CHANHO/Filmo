package com.moive.test.controller;

import com.moive.test.DTO.userDTO;
import com.moive.test.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController {

    @Autowired
    private loginService loginService;

    @PostMapping("/login")
    public String login(String uid, String type){
        userDTO logingUser = userDTO.builder()
                .uid(uid)
                .type(type)
                .build();

        String isExistUser = loginService.isExistUser(logingUser);

        return isExistUser;
    }
}
