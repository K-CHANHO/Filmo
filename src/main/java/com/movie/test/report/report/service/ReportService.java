package com.movie.test.report.report.service;

import com.movie.test.report.report.dto.ReportDto;
import com.movie.test.report.report.dto.ReportSearchDTO;
import com.movie.test.report.report.dto.ReportSaveDto;
import com.movie.test.report.report.dto.ReportUpdateDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ReportService {

    // 감상문 등록
    String saveReport(ReportSaveDto reportSaveDto);

    // 감상문 조회
    ReportDto getReport(String reportId);

    // 감상문 수정
    String updateReport(ReportUpdateDto reportUpdateDto);

    // 감상문 삭제
    void deleteReport(String reportId);

    // 감상문 검색 리스트 조회를 위한 reportId 리스트 조회
    Slice<String> getSearchReportId(ReportSearchDTO reportSearchDTO, Pageable pageable);

    // 감상문 검색 결과 갯수
    Long getSearchReportCount(ReportSearchDTO reportSearchDTO);

    // 특정 사용자가 작성한 감상문 조회
    List<String> getReportIdByUserId(String userId);

    // reportId 값이 존재하는 지 확인
    boolean validationReportId(String reportId);

}
