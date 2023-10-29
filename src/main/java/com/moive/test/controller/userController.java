package com.moive.test.controller;

import com.moive.test.DTO.userDTO;
import com.moive.test.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    @Autowired
    private userService userService;

    @PostMapping("/signup")
    public userDTO userCreate(userDTO userDTO) {
        userDTO newUser = userService.userSave(userDTO);

        return newUser;

    }
}
