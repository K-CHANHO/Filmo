package com.movie.test.report.complaint.controller;

import com.movie.test.report.complaint.dto.ComplaintDTO;
import com.movie.test.report.complaint.service.ComplaintService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "감상문 신고", description = "신고 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/complaint")
public class ComplaintController {

    private final ComplaintService complaintService;

    @Operation(summary = "감상문 신고", description = "신고 내용을 DB에 저장합니다.")
    @Parameters(value = {
//            @Parameter(name = "userId", description = "신고자 id"),
            @Parameter(name = "reportId", description = "신고당한 감상문 id"),
            @Parameter(name = "content", description = "신고내용")
    })
    @PostMapping("/registComplaint")
    public ResponseEntity registComplaint(ComplaintDTO complaintDTO) {

        ComplaintDTO registedComplaint = complaintService.registComplaint(complaintDTO);
        log.info("[{}] 님이 [{}] 감상문을 신고하였습니다.", complaintDTO.getUserId(), complaintDTO.getReportId());

        return new ResponseEntity(registedComplaint.getComplaintId(), HttpStatus.OK);
    }

    @Operation(summary = "감상문 신고 취소", description = "감상문 신고를 취소합니다.")
    @Parameters(value = {
//            @Parameter(name = "userId", description = "신고자 id"),
            @Parameter(name = "reportId", description = "신고당한 감상문 id"),
    })
    @PostMapping("/deleteComplaint/{complaintId}")
    public ResponseEntity deleteComplaint(String userId, String reportId){

        complaintService.deleteById(userId, reportId);

        return new ResponseEntity(HttpStatus.OK);
    }

}
