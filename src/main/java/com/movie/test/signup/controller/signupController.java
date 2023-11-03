package com.movie.test.signup.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.movie.test.login.controller.loginController;
import com.movie.test.user.dto.userDTO;
import com.movie.test.user.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class signupController {

    @Autowired
    private userService userService;

    @Autowired
    private loginController loginController;

    @PostMapping("/signup")
    public ResponseEntity userCreate(userDTO userDTO) {
    
        /* TODO : 랜덤 닉네임 부여 => 따로 메서드 만들기
        userDTO.setNickname();
         */
        userDTO newUser = userService.userSave(userDTO);

        JsonObject serverData = new JsonObject();
        serverData.addProperty("uid", newUser.getUid());
        serverData.addProperty("type", newUser.getType());

        JsonArray userinfo = new JsonArray();
        userinfo.add(serverData);

        return new ResponseEntity(userinfo, HttpStatus.OK);

    }
}
