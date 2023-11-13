package com.movie.test.test.controller;

import com.google.gson.JsonObject;
import com.movie.test.s3.service.S3Service;
import com.movie.test.test.dto.testDTO;
import com.movie.test.test.service.testService;
import com.movie.test.token.service.tokenService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
public class test {

    @Autowired
    private testService testService;

    @Autowired
    private tokenService tokenService;

    @Autowired
    private S3Service s3Service;

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

    @PostMapping("/imageUrlTest")
    @ResponseBody
    public String imageUrlTest(String imageUrl){
        return s3Service.uploadImage(imageUrl);
    }

    @PostMapping("/testTokenRefresh")
    @ResponseBody
    public ResponseEntity testRefreshToken(String token, ServletRequest request, ServletResponse response){

        HttpServletRequest req = (HttpServletRequest) request;
        String newToken = req.getHeader("token");
        JsonObject serverData = new JsonObject();
        serverData.addProperty("originToken", token);
        serverData.addProperty("newToken", newToken);

        return new ResponseEntity(serverData, HttpStatus.OK);
    }
}
