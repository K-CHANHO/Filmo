package com.movie.test.report.report.service;

import com.movie.test.report.report.dto.ReportDTO;

import java.util.List;

public interface ReportService {

    String registReport(ReportDTO reportDTO);

    List<ReportDTO> getAllReports();
    ReportDTO getReport(String reportId);

    String modifyReport(ReportDTO reportDTO);

    void deleteReport(String reportId);

    List<ReportDTO> getSearchReports(String keyword);

}
