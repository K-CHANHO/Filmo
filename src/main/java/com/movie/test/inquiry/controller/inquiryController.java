package com.movie.test.inquiry.controller;

import com.movie.test.inquiry.dto.inquiryDTO;
import com.movie.test.inquiry.service.inquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
public class inquiryController {

    @Autowired
    private inquiryService inquiryService;

    @PostMapping("/inquiry/regist")
    public ResponseEntity registInquiry(inquiryDTO inquiryDTO) {
        inquiryService.registInquiry(inquiryDTO);

        return new ResponseEntity("success", HttpStatus.OK);
    }
}
