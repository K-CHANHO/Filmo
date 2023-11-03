package com.movie.test.signup.controller;

import com.google.gson.JsonObject;
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

    @PostMapping("/signup")
    public ResponseEntity userCreate(userDTO userDTO) {

        userDTO newUser = userService.userSave(userDTO);

        JsonObject serverData = new JsonObject();

        return new ResponseEntity<>(serverData, HttpStatus.OK);

    }
}
