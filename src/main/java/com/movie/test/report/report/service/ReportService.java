package com.movie.test.report.report.service;

import com.movie.test.report.report.dto.ReportDTO;
import com.movie.test.report.report.dto.ReportListSearchDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ReportService {

    // 감상문 등록
    String registReport(ReportDTO reportDTO);

    // 감상문 조회
    ReportDTO getReport(String reportId);

    // 감상문 수정
    String modifyReport(ReportDTO reportDTO);

    // 감상문 삭제
    void deleteReport(String reportId);

    // 감상문 검색 리스트 조회를 위한 reportId 리스트 조회
    Slice<String> getSearchReportId(ReportListSearchDTO reportListSearchDTO, Pageable pageable);

    // 감상문 검색 결과 갯수
    Long getSearchReportCount(ReportListSearchDTO reportListSearchDTO);

}
