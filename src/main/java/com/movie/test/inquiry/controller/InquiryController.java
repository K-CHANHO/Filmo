package com.movie.test.inquiry.controller;

import com.movie.test.inquiry.dto.InquiryDto;
import com.movie.test.inquiry.dto.InquirySaveDto;
import com.movie.test.inquiry.dto.InquiryUpdateDto;
import com.movie.test.inquiry.service.InquiryService;
import com.movie.test.user.userinfo.dto.CustomUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "문의사항", description = "문의사항 관련 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;

    @Operation(summary = "문의사항 등록", description = "파라미터로 받은 내용을 DB에 저장하고 관리자에게 메일을 보냅니다.")
    @PostMapping("/save")
    public ResponseEntity saveInquiry(@RequestBody InquirySaveDto inquirySaveDto, @AuthenticationPrincipal CustomUser user) {

        inquirySaveDto.setUserId(user.getUserId());
        inquiryService.registInquiry(inquirySaveDto);

        log.info("{} 님이 문의를 등록하였습니다.", inquirySaveDto.getUserId());

        return new ResponseEntity("success", HttpStatus.OK);
    }

    @Operation(summary = "문의사항 상세 조회", description = "문의사항을 조회합니다.")
    @GetMapping("/get/{inquiryId}")
    public ResponseEntity getInquiry(@PathVariable String inquiryId) {

        try{
            InquiryDto inquiryDTO = inquiryService.getInquiry(inquiryId);
            return new ResponseEntity(inquiryDTO, HttpStatus.OK);
        } catch (NullPointerException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "문의사항 리스트 조회", description = "문의사항의 리스트를 조회합니다.")
    @Parameter(name = "lastInquiryId", description = "마지막에 조회된 문의사항 ID")
    @GetMapping("/getList")
    public ResponseEntity getInquiryList(@AuthenticationPrincipal CustomUser user, @Nullable String lastInquiryId, @Parameter(hidden = true) Pageable pageable){

        Map<String, Object> resultData = new HashMap<>();

        Slice<InquiryDto> inquiryList = inquiryService.getInquiryList(user.getUserId(), lastInquiryId, pageable);

        resultData.put("inquiryList", inquiryList.getContent());
        resultData.put("hasNext", inquiryList.hasNext());

        return new ResponseEntity(resultData, HttpStatus.OK);
    }

    @Operation(summary = "문의사항 답변여부 변경")
    @PatchMapping("/update/answer")
    public ResponseEntity updateInquiryAnswerYn(@Valid @RequestBody InquiryUpdateDto inquiryUpdateDto){

        inquiryService.updateAnswerYn(inquiryUpdateDto);

        return new ResponseEntity(HttpStatus.OK);
    }
}
