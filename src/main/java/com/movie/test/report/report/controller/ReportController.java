package com.movie.test.report.report.controller;

import com.movie.test.report.ReportCompactService;
import com.movie.test.report.report.dto.ReportDTO;
import com.movie.test.report.report.dto.ReportListSearchDTO;
import com.movie.test.report.report.dto.ReportSimpleDTO;
import com.movie.test.report.report.service.ReportService;
import com.movie.test.report.view.service.ViewService;
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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "감상문", description = "감상문 관련 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;
    private final ViewService viewService;

    private final ReportCompactService reportCompactService;

    @Operation(summary = "감상문 등록", description = "감상문을 등록합니다.")
    @Parameters({
            @Parameter(name = "userId", description = "작성자 id", required = true),
            @Parameter(name = "title", description = "감상문 제목", required = true),
            @Parameter(name = "content", description = "감상문 내용", required = true),
            @Parameter(name = "movieId", description = "선택한 영화 id (TMDB)", required = true),
            @Parameter(name = "tagString", description = "해쉬태그", required = true, example = "#한국영화#액션#꿀잼")
    })
    @ApiResponse(responseCode = "200", description = "등록된 감상문 id 리턴")
    @PostMapping("/registReport")
    public ResponseEntity registReport(ReportDTO reportDTO) {

        String reportId = reportCompactService.registReport(reportDTO);

        return new ResponseEntity(reportId, HttpStatus.OK);
    }

    @Operation(summary = "감상문 조회", description = "감상문을 조회합니다.")
    @Parameter(name = "reportId", description = "조회할 감상문의 id", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "감상문 정보 및 댓글 정보")
    })
    @GetMapping("/getReport/{reportId}")
    public ResponseEntity getReport(@PathVariable String reportId){

        // 리턴값
        Map<String, Object> serverData = new HashMap<>();

        // 감상문
        // 해당 감상문 없을 경우 에러메시지 담아서 리턴
        ReportDTO singleReport = reportCompactService.getSingleReport(reportId);
        if(singleReport == null){
            serverData.put("error", "해당 게시물은 존재하지 않습니다");
            return new ResponseEntity(serverData, HttpStatus.valueOf(404));
        }

        // 댓글 페이지 따로 있는 경우 따로 호출.

        // 조회수 증가 TODO: 추후 REDIS로 구현해보기
        viewService.addViewCount(reportId);

        return new ResponseEntity(singleReport, HttpStatus.OK);
    }

    @Operation(summary = "감상문 수정", description = "감상문을 수정합니다.")
    @Parameters(value = {
            @Parameter(name = "reportId", description = "수정할 감상문의 id", required = true),
            @Parameter(name = "title", description = "수정할 감상문의 제목", required = true),
            @Parameter(name = "content", description = "수정할 감상문의 내용", required = true),
            @Parameter(name = "movieId", description = "선택한 영화 id (TMDB)", required = true),
            @Parameter(name = "tagString", description = "해쉬태그", required = true, example = "#한국영화#액션#꿀잼")
    })
    @PostMapping("/modifyReport")
    public ResponseEntity modifyReport(ReportDTO reportDTO){

        String reportId = reportCompactService.modifyReport(reportDTO);

        return new ResponseEntity(reportId, HttpStatus.OK);
    }

    @Operation(summary = "감상문 삭제", description = "감상문을 삭제하고 관련 데이터(댓글, 태그, 신고내역)도 삭제합니다.")
    @Parameter(name = "reportId", description = "삭제할 감상문의 id", required = true)
    @GetMapping("/deleteReport/{reportId}")
    public ResponseEntity deleteReport(@PathVariable String reportId){

        reportCompactService.deleteReport(reportId);

        return new ResponseEntity("Success Delete Report", HttpStatus.OK);
    }

    @Operation(summary = "감상문 검색", description = "감상문을 검색합니다. 검색어가 없을 시 전체 감상문을 조회합니다.")
    @Parameters({
            @Parameter(name = "lastReportId", description = "마지막으로 조회된 감상문 id"),
            @Parameter(name = "keyword", description = "검색어"),
    })
    @ApiResponse(responseCode = "200", description = "팔로워 목록 리턴")
    @GetMapping("/searchReport")
    public ResponseEntity getSearchReport(ReportListSearchDTO reportListSearchDTO, Pageable pageable){

        Slice<ReportSimpleDTO> searchReport = reportCompactService.getReportList(reportListSearchDTO, pageable);
        Long searchReportCount = reportService.getSearchReportCount(reportListSearchDTO);

        Map<String, Object> resultData = new HashMap<>();

        resultData.put("searchReportCount", searchReportCount);
        resultData.put("reportList", searchReport.getContent());
        resultData.put("hasNext", searchReport.hasNext());

        return new ResponseEntity(resultData, HttpStatus.OK);
    }

    @GetMapping("/otherReportOfUser")
    public ResponseEntity getOtherReport(String userId) {

        List<String> reportIdByUserId = reportService.getReportIdByUserId(userId);

        List<ReportSimpleDTO> reportSimpleDTOS = reportIdByUserId.stream().map(reportCompactService::getSimpleReport).toList();


        return new ResponseEntity(reportSimpleDTOS, HttpStatus.OK);
    }

}

