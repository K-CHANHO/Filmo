package com.movie.test.report.controller;

import com.movie.test.reply.dto.ReplyDTO;
import com.movie.test.reply.service.ReplyService;
import com.movie.test.report.dto.ReportDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.movie.test.report.service.ReportService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "감상문", description = "감상문 관련 API")
@RestController
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReplyService replyService;


    @Operation(summary = "감상문 등록", description = "감상문을 등록합니다.")
    @Parameters({
            @Parameter(name = "userId", description = "작성자 id", required = true),
            @Parameter(name = "title", description = "감상문 제목", required = true),
            @Parameter(name = "content", description = "감상문 내용", required = true)
    })
    @ApiResponse(responseCode = "200", description = "등록된 감상문 id 리턴")
    @PostMapping("/registReport")
    public ResponseEntity registReport(ReportDTO reportDTO) {

        log.info("Start Report Controller : registReport");

        String reportId = reportService.registReport(reportDTO);

        log.info("End Report Controller : registReport");
        return new ResponseEntity(reportId, HttpStatus.OK);
    }

    @Operation(summary = "감상문 조회", description = "감상문을 조회합니다.")
    @Parameter(name = "reportId", description = "조회할 감상문의 id", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "감상문 정보 및 댓글 정보")
    })
    @GetMapping("/getReport/{reportId}")
    public ResponseEntity getReport(@PathVariable String reportId){

        ReportDTO report = reportService.getReport(reportId);
        List<ReplyDTO> replies = replyService.getReplies(reportId);

        Map<String, Object> serverData = new HashMap<>();
        serverData.put("report", report);
        serverData.put("replies", replies);

        return new ResponseEntity(serverData, HttpStatus.OK);
    }

    @Operation(summary = "감상문 수정", description = "감상문을 수정합니다.")
    @Parameters(value = {
            @Parameter(name = "reportId", description = "수정할 감상문의 id", required = true),
            @Parameter(name = "title", description = "수정할 감상문의 제목", required = true),
            @Parameter(name = "content", description = "수정할 감상문의 내용", required = true)
    })
    @PostMapping("/modifyReport")
    public ResponseEntity modifyReport(ReportDTO report){

        String reportId = reportService.modifyReport(report);

        return new ResponseEntity(reportId, HttpStatus.OK);
    }

    @Operation(summary = "감상문 삭제", description = "감상문을 삭제하고 해당 댓글도 삭제합니다.")
    @Parameter(name = "reportId", description = "삭제할 감상문의 id", required = true)
    @GetMapping("/deleteReport/{reportId}")
    public ResponseEntity deleteReport(@PathVariable String reportId){
        reportService.deleteReport(reportId);
        replyService.deleteRepliesByReportId(reportId);

        return new ResponseEntity("Success Delete Report", HttpStatus.OK);
    }
}
