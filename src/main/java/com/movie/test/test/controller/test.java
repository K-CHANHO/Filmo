package com.movie.test.test.controller;

import com.google.gson.JsonObject;
import com.movie.test.hashtag.service.TagService;
import com.movie.test.s3.service.S3Service;
import com.movie.test.test.dto.testDTO;
import com.movie.test.test.service.testService;
import com.movie.test.token.service.TokenService;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Hidden
@Controller
@RequestMapping("/test")
public class test {

    @Autowired
    private testService testService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private TagService tagService;

    @GetMapping("/test")
    public String index(){
        return "index";
    }

    @PostMapping("/test")
    @ResponseBody
    public testDTO test(testDTO param) {

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
    public ResponseEntity testRefreshToken(String token, HttpServletRequest request, HttpServletResponse response){

        String originToken  = (String) request.getHeader("token");
        String newToken =  (String) response.getHeader("token");
//        String originToken = response.getHeader("originToken");
//        String newToken = response.getHeader("newToken");

        JsonObject serverData = new JsonObject();
        serverData.addProperty("originToken", originToken);
        serverData.addProperty("newToken", newToken);

        return new ResponseEntity(serverData, HttpStatus.OK);
    }

    @PostMapping("/getTags")
    @ResponseBody
    public ResponseEntity getTagsTest(String reportId) {

        List<String> tagsInReport = tagService.getTagsInReport(reportId);

        return new ResponseEntity(tagsInReport, HttpStatus.OK);
    }



}
