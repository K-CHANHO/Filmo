package com.movie.test.report.controller;

import com.movie.test.report.dto.reportDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.movie.test.report.service.reportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class reportController {

    @Autowired
    private reportService reportService;

    @PostMapping("/registReport")
    @ResponseBody
    public ResponseEntity registReport(reportDTO reportDTO) {

        log.info("Start Report Controller : registReport");

        reportService.regist(reportDTO);

        log.info("End Report Controller : registReport");
        return new ResponseEntity(HttpStatus.OK);
    }
}
