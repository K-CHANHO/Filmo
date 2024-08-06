package com.movie.test.report.report.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.movie.test.report.ReportCompactService;
import com.movie.test.report.report.dto.*;
import com.movie.test.report.report.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "감상문", description = "감상문 관련 API")
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/report")
public class ReportController {

    private final Gson gson;
    private final ReportService reportService;
    private final ReportCompactService reportCompactService; // 하나의 트랜잭션으로 묶기 위해 하나의 서비스에 통합

    @Operation(summary = "감상문 등록", description = "감상문을 등록합니다.")
    @ApiResponse(responseCode = "200", description = "등록된 감상문 id 리턴")
    @PostMapping("/save")
    public ResponseEntity saveReport(@RequestBody ReportSaveDto reportSaveDto) {

        String reportId = reportCompactService.saveReport(reportSaveDto);

        JsonObject returnData = new JsonObject();
        returnData.addProperty("value", reportId);

        return new ResponseEntity(returnData, HttpStatus.OK);
    }

    @Operation(summary = "감상문 조회", description = "감상문을 조회합니다.")
    @Parameter(name = "reportId", description = "조회할 감상문의 id", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "감상문 정보 및 댓글 정보"),
            @ApiResponse(responseCode = "404", description = "감상문이 없을 경우 404 리턴")
    })
    @GetMapping("/getReport/{reportId}")
    public ResponseEntity getReport(@PathVariable String reportId) {

        // 리턴값
        JsonObject returnData = new JsonObject();

        // 감상문이 없으면
        if (!reportService.validationReportId(reportId)) {
            returnData.addProperty("msg", "감상문이 존재하지 않습니다.");
            returnData.addProperty("status", "404");
            return new ResponseEntity(returnData, HttpStatus.NOT_FOUND);
        } else {
            ReportDto singleReport = reportCompactService.getSingleReport(reportId);

            // 댓글 페이지 따로 있는 경우 따로 호출.

            // 조회수 증가 TODO: 추후 REDIS로 구현해보기
            // viewService.addViewCount(reportId);

            String jsonReport = gson.toJson(singleReport);
            returnData.addProperty("value", jsonReport);

            return new ResponseEntity(returnData, HttpStatus.OK);
        }
    }

    @Operation(summary = "감상문 수정", description = "감상문을 수정합니다.")
    @ApiResponse(responseCode = "200", description = "수정된 감상문 id 리턴")
    @PostMapping("/updateReport")
    public ResponseEntity updateReport(@RequestBody ReportUpdateDto reportUpdateDto){

        String reportId = reportCompactService.updateReport(reportUpdateDto);

        // 리턴 값을 위한 객체 value, msg, status로 구성
        JsonObject returnData = new JsonObject();
        returnData.addProperty("value", reportId);

        return new ResponseEntity(returnData, HttpStatus.OK);
    }

    @Operation(summary = "감상문 삭제", description = "감상문을 삭제하고 관련 데이터(댓글, 태그, 신고내역)도 삭제합니다.")
    @Parameter(name = "reportId", description = "삭제할 감상문의 id", required = true)
    @GetMapping("/deleteReport/{reportId}")
    public ResponseEntity deleteReport(@PathVariable String reportId){

        reportCompactService.deleteReport(reportId);

        return new ResponseEntity("Success Delete Report", HttpStatus.OK);
    }

    @Operation(summary = "감상문 검색", description = "감상문을 검색합니다. 검색어가 없을 시 전체 감상문을 조회합니다. 다른 사용자가 작성한 감상문을 조회하려면 userId 값을 추가해주세요.")
    @ApiResponse(responseCode = "200", description = "팔로워 목록 리턴")
    @PostMapping("/searchReport")
    public ResponseEntity getSearchReport(@RequestBody ReportSearchDTO reportSearchDTO, @Parameter(hidden = true) Pageable pageable){

        Slice<ReportSimpleDTO> searchReport = reportCompactService.getReportList(reportSearchDTO, pageable);
        Long searchReportCount = reportService.getSearchReportCount(reportSearchDTO);

        String searchReportJson = gson.toJson(searchReport.getContent());

        JsonObject returnData = new JsonObject();
        returnData.addProperty("searchReportCount", searchReportCount);
        returnData.addProperty("reportList", searchReportJson);
        returnData.addProperty("hasNext", searchReport.hasNext());

        return new ResponseEntity(returnData, HttpStatus.OK);
    }


    @Operation(summary = "다른 유저의 감상문 검색", description = "다른 유저가 작성한 감상문을 검색합니다.", deprecated = true)
    @Parameters({
            @Parameter(name = "otherUserId", description = "다른 유저 id"),
    })
    @GetMapping("/otherReportOfUser")
    public ResponseEntity getOtherReport(String otherUserId) {

        List<String> reportIdByUserId = reportService.getReportIdByUserId(otherUserId);

        List<ReportSimpleDTO> reportSimpleDTOS = reportIdByUserId.stream().map(reportCompactService::getSimpleReport).toList();

        return new ResponseEntity(reportSimpleDTOS, HttpStatus.OK);
    }

}

