package com.movie.test.report.service;

import com.movie.test.report.dto.reportDTO;
import com.movie.test.report.entity.reportEntity;
import com.movie.test.report.repository.reportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class reportServiceImpl implements reportService {

    @Autowired
    private reportRepository reportRepository;

    @Override
    public String registReport(reportDTO reportDTO) {
        log.info("Start Report Service : regist");
        reportDTO.setReportId(UUID.randomUUID().toString());
        reportRepository.save(dtoTOentity(reportDTO));

        log.info("End Report Service : regist");
        return reportDTO.getReportId();
    }

    @Override
    public reportDTO getReport(String reportId) {
        reportDTO reportDTO = entityTOdto(reportRepository.findById(reportId).get());

        return reportDTO;
    }

    @Override
    public String modifyReport(reportDTO reportDTO) {

        reportEntity modifiedReport = reportRepository.save(dtoTOentity(reportDTO));

        return modifiedReport.getReportId();
    }

    @Override
    public void deleteReport(String reportId) {
        reportRepository.deleteById(reportId);
    }
}
