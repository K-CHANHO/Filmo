package com.movie.test.report.report.service;

import com.movie.test.report.report.dto.ReportDTO;
import com.movie.test.report.report.dto.ReportListSearchDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ReportService {

    String registReport(ReportDTO reportDTO);

    ReportDTO getReport(String reportId);

    String modifyReport(ReportDTO reportDTO);

    void deleteReport(String reportId);

    Slice<String> getSearchReportId(ReportListSearchDTO reportListSearchDTO, Pageable pageable);

}
