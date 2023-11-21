package com.movie.test.user.controller;

import com.movie.test.user.dto.userDTO;
import com.movie.test.user.service.userService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class userController {

    @Autowired
    userService userService;

    @GetMapping("/userinfo")
    @ResponseBody
    public ResponseEntity userinfo(userDTO userDTO, HttpServletRequest request, HttpServletResponse response) {

        userDTO userinfo = userService.getUserInfo(userDTO.getUserId());

        HashMap<String, Object> userinfoMap = new HashMap<>();
        userinfoMap.put("userinfo", userinfo);
        userinfoMap.put("newToken", request.getAttribute("newToken"));

        return new ResponseEntity(userinfoMap, HttpStatus.OK);
    }
}
