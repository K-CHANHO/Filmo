package com.movie.test.report.service;

import com.movie.test.report.dto.reportDTO;
import com.movie.test.report.entity.reportEntity;

import java.nio.charset.StandardCharsets;

public interface reportService {

    String regist(reportDTO reportDTO);


    default reportEntity dtoTOentity(reportDTO reportDTO) {
        reportEntity report = reportEntity.builder()
                .reportId(reportDTO.getReportId())
                .title(reportDTO.getTitle())
                .content(reportDTO.getContent().getBytes(StandardCharsets.UTF_8))
                .userid(reportDTO.getUserid())
                .register_date(reportDTO.getCreate_date())
                .update_date(reportDTO.getUpdate_date())
                .build();

        return report;


    }
}
