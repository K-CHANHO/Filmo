package com.movie.test.report.service;

import com.movie.test.report.dto.reportDTO;
import com.movie.test.report.entity.reportEntity;
import com.movie.test.report.repository.reportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
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
        reportEntity originReport = reportRepository.findById(reportDTO.getReportId()).get();
        reportEntity newReport = reportEntity.builder()
                        .title(reportDTO.getTitle())
                        .content(reportDTO.getContent().getBytes(StandardCharsets.UTF_8))
                        .userId(originReport.getUserId())
                        .createDate(originReport.getCreateDate())
                        .reportId(originReport.getReportId())
                        .build();


        reportRepository.save(newReport);

        return newReport.getReportId();
    }

    @Override
    public void deleteReport(String reportId) {
        reportRepository.deleteById(reportId);
    }
}
