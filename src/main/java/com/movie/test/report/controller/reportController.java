package com.movie.test.report.controller;

import com.google.gson.*;
import com.movie.test.reply.dto.replyDTO;
import com.movie.test.reply.service.replyService;
import com.movie.test.report.dto.reportDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.movie.test.report.service.reportService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class reportController {

    @Autowired
    private reportService reportService;

    @Autowired
    private replyService replyService;


    @PostMapping("/registReport")
    public ResponseEntity registReport(reportDTO reportDTO) {

        log.info("Start Report Controller : registReport");

        String reportId = reportService.registReport(reportDTO);

        log.info("End Report Controller : registReport");
        return new ResponseEntity(reportId, HttpStatus.OK);
    }

    @GetMapping("/getReport/{reportId}")
    public ResponseEntity getReport(@PathVariable String reportId){

        reportDTO report = reportService.getReport(reportId);
        List<replyDTO> replies = replyService.getReplies(reportId);

        Map<String, Object> serverData = new HashMap<>();
        serverData.put("report", report);
        serverData.put("replies", replies);

        return new ResponseEntity(serverData, HttpStatus.OK);
    }

    @PostMapping("/modifyReport")
    public ResponseEntity modifyReport(reportDTO report){

        String reportId = reportService.modifyReport(report);

        return new ResponseEntity(reportId, HttpStatus.OK);
    }

    @GetMapping("/deleteReport/{reportId}")
    public ResponseEntity deleteReport(@PathVariable String reportId){
        reportService.deleteReport(reportId);
        replyService.deleteRepliesByReportId(reportId);

        return new ResponseEntity("Success Delete Report", HttpStatus.OK);
    }
}
