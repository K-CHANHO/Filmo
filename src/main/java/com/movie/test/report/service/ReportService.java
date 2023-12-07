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


    default ReportEntity dtoTOentity(ReportDTO reportDTO) {
        ReportEntity report = ReportEntity.builder()
                .reportId(reportDTO.getReportId())
                .title(reportDTO.getTitle())
                .content(reportDTO.getContent().getBytes(StandardCharsets.UTF_8))
                .userId(reportDTO.getUserId())
                .build();

        return report;
    }

    default ReportDTO entityTOdto(ReportEntity reportEntity){
        ReportDTO report = ReportDTO.builder()
                .reportId(reportEntity.getReportId())
                .title(reportEntity.getTitle())
                .content(new String(reportEntity.getContent(), StandardCharsets.UTF_8))
                .userId(reportEntity.getUserId())
                .createDate(reportEntity.getCreateDate())
                .lastModifiedDate(reportEntity.getLastModifiedDate())
                .build();

        return report;
    }
}
