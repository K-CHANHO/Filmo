package com.moive.test.controller;

import com.moive.test.DTO.testDTO;
import com.moive.test.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class test {

    @Autowired
    private testService testService;
    @PostMapping("/test")
    @ResponseBody
    public testDTO test(testDTO param){

        testService.insert(param);

        return param;
    }
}
