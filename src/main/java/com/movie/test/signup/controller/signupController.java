package com.movie.test.signup.controller;

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

    /**
     * 회원가입 로직
     * @param userDTO : uid(소셜에서 주는 정보), type, profileURL
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity userCreate(userDTO userDTO) {

        userDTO newUser = userService.newUserSave(userDTO);

        JsonObject userinfo = new JsonObject();
        userinfo.addProperty("uid", newUser.getUid());
        userinfo.addProperty("userid", newUser.getUserId());
        userinfo.addProperty("type", newUser.getType());
        userinfo.addProperty("nickname", newUser.getNickname());
        userinfo.addProperty("profileURL", newUser.getProfileURL());
        userinfo.addProperty("last_login_date", String.valueOf(newUser.getLast_login_date()));
        userinfo.addProperty("create_date", String.valueOf(newUser.getCreate_date()));

        JsonObject serverData = new JsonObject();
        serverData.add("userinfo", userinfo);


        return new ResponseEntity(serverData, HttpStatus.OK);

    }
}
