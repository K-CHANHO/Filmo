package com.moive.test;

import com.moive.test.DTO.testDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class test {

    @PostMapping("/test")
    @ResponseBody
    public testDTO test(testDTO param){
        return param;
    }
}
