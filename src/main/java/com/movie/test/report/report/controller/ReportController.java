package com.movie.test.report.report.controller;

import com.movie.test.report.ReportCompactService;
import com.movie.test.report.complaint.service.ComplaintService;
import com.movie.test.report.hashtag.service.TagService;
import com.movie.test.report.reply.dto.ReplyDTO;
import com.movie.test.report.reply.service.ReplyService;
import com.movie.test.report.report.dto.ReportDTO;
import com.movie.test.report.report.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final ReplyService replyService;
    private final ComplaintService complaintService;
    private final TagService tagService;

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
        ReportDTO report = reportService.getReport(reportId);
        if(report == null){
            serverData.put("error", "해당 게시물은 존재하지 않습니다");
            return new ResponseEntity(serverData, HttpStatus.valueOf(404));
        }

        // 사용한 태그
        List<String> tagsInReport = tagService.getTagsInReport(reportId);
        String tagString = String.join("#", tagsInReport);
        report.setTagString("#" + tagString);

        // 신고당한 횟수
        long complaintCount = complaintService.getComplaintCount(reportId);
        report.setComplaintCount(complaintCount);

        // 댓글
        List<ReplyDTO> replies = replyService.getReplies(reportId);

        serverData.put("report", report);
        serverData.put("replies", replies);

        return new ResponseEntity(serverData, HttpStatus.OK);
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

    @Operation(summary = "모든 감상문 조회", description = "모든 감상문을 조회합니다.")
    @GetMapping("/report/getAllReports")
    public ResponseEntity getAllReports(){
        List<ReportDTO> allReports = reportService.getAllReports();

        return new ResponseEntity(allReports, HttpStatus.OK);
    }

    @GetMapping("/report/search/{keyword}")
    public ResponseEntity searchReports(@PathVariable String keyword){
        List<ReportDTO> searchReports = reportService.getSearchReports(keyword);

        return new ResponseEntity(searchReports, HttpStatus.OK);
    }
}

