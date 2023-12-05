package com.movie.test.inquiry.controller;

import com.movie.test.inquiry.dto.inquiryDTO;
import com.movie.test.inquiry.service.inquiryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "문의사항", description = "문의사항 관련 API")
@RestController
@Slf4j
public class InquiryController {

    @Autowired
    private inquiryService inquiryService;

    @Operation(summary = "문의사항 등록", description = "파라미터로 받은 내용을 DB에 저장하고 관리자에게 메일을 보냅니다.")
    @Parameters({
        @Parameter(name = "userId", description = "유저아이디", required = true),
        @Parameter(name = "title", description = "제목", required = true),
        @Parameter(name = "content", description = "문의 내용", required = true),
        @Parameter(name = "userEmail", description = "답변받을 이메일", required = true),
    })
    @PostMapping("/inquiry/regist")
    public ResponseEntity registInquiry(inquiryDTO inquiryDTO) {
        inquiryService.registInquiry(inquiryDTO);

        return new ResponseEntity("success", HttpStatus.OK);
    }
}
