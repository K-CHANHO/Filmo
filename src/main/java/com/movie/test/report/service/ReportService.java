package com.movie.test.report.service;

import com.movie.test.report.dto.ReportDTO;
import com.movie.test.report.entity.ReportEntity;

import java.nio.charset.StandardCharsets;
import java.util.List;

public interface ReportService {

    String registReport(ReportDTO reportDTO);

    List<ReportDTO> getAllReports();
    ReportDTO getReport(String reportId);

    String modifyReport(ReportDTO reportDTO);

    void deleteReport(String reportId);

    List<ReportDTO> getSearchReports(String keyword);

}
