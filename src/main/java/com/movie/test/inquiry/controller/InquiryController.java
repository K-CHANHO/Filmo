package com.movie.test.inquiry.controller;

import com.movie.test.inquiry.dto.InquiryDTO;
import com.movie.test.inquiry.service.InquiryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "문의사항", description = "문의사항 관련 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;

    @Operation(summary = "문의사항 등록", description = "파라미터로 받은 내용을 DB에 저장하고 관리자에게 메일을 보냅니다.")
    @Parameters({
        @Parameter(name = "userId", description = "문의 등록 유저아이디", required = true),
        @Parameter(name = "title", description = "문의 제목", required = true),
        @Parameter(name = "content", description = "문의 내용", required = true),
        @Parameter(name = "type", description = "문의 유형", required = true),
        @Parameter(name = "userEmail", description = "답변받을 이메일", required = true)
    })
    @PostMapping("/regist")
    public ResponseEntity registInquiry(InquiryDTO inquiryDTO) {
        inquiryService.registInquiry(inquiryDTO);
        log.info("{} 님이 문의를 등록하였습니다.", inquiryDTO.getUserId());

        return new ResponseEntity("success", HttpStatus.OK);
    }

    @GetMapping("/getInquiry/{inquiryId}")
    public ResponseEntity getInquiry(@PathVariable String inquiryId) {

        try{
            InquiryDTO inquiryDTO = inquiryService.getInquiry(inquiryId);
            return new ResponseEntity(inquiryDTO, HttpStatus.OK);
        } catch (NullPointerException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
