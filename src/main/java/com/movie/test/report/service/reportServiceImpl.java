package com.movie.test.report.service;

import com.movie.test.report.dto.reportDTO;
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
    public String regist(reportDTO reportDTO) {
        log.info("Start Report Service : regist");
        reportDTO.setReportId(UUID.randomUUID().toString());
        reportRepository.save(dtoTOentity(reportDTO));

        log.info("End Report Service : regist");
        return reportDTO.getReportId();
    }
}
