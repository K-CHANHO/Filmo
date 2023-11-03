package com.moive.test.test.controller;

import com.moive.test.test.dto.testDTO;
import com.moive.test.test.service.testService;
import com.moive.test.token.service.tokenService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class test {

    @Autowired
    private testService testService;

    @Autowired
    private tokenService tokenService;

    @PostMapping("/test")
    @ResponseBody
    public testDTO test(testDTO param){

        testService.insert(param);

        return param;
    }

    @PostMapping("/getPayloadTest")
    @ResponseBody
    public Claims getPayloadTest(String jwt){
        return tokenService.readJwtToken(jwt);
    }
}
