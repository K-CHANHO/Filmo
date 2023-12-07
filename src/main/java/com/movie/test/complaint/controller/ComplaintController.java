package com.movie.test.complaint.controller;

import com.movie.test.complaint.dto.ComplaintDTO;
import com.movie.test.complaint.service.ComplaintService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "감상문 신고", description = "신고 API")
@RestController
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @Operation(summary = "감상문 신고", description = "신고 내용을 DB에 저장합니다.")
    @Parameters(value = {
            @Parameter(name = "complaintUser", description = "신고자 id"),
            @Parameter(name = "getComplaintReportId", description = "신고당한 감상문 id"),
            @Parameter(name = "content", description = "신고내용")
    })
    @PostMapping("/complaint/regist")
    public String registComplaint(ComplaintDTO complaintDTO) {

        ComplaintDTO registedComplaint = complaintService.registComplaint(complaintDTO);

        return registedComplaint.getComplaintId();

    }
}
