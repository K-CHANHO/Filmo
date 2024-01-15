package com.movie.test.report.service;

import com.movie.test.complaint.repository.ComplaintRepository;
import com.movie.test.report.dto.ReportDTO;
import com.movie.test.report.entity.ReportEntity;
import com.movie.test.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ComplaintRepository complaintRepository;

    @Override
    public String registReport(ReportDTO reportDTO) {
        log.info("Start Report Service : regist");
        reportDTO.setReportId(UUID.randomUUID().toString());
        reportRepository.save(ReportDTO.toEntity(reportDTO));

        log.info("End Report Service : regist");
        return reportDTO.getReportId();
    }

    @Override
    public List<ReportDTO> getAllReports() {
        List<ReportEntity> reportEntities = reportRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));

        List<ReportDTO> reportDTOS = new ArrayList<>();
        reportEntities.forEach((entity) -> {
            ReportDTO dto = ReportDTO.toDTO(entity);
            dto.setComplaintCount(complaintRepository.countByReportId(dto.getReportId()));
            reportDTOS.add(dto);
        });

        return reportDTOS;
    }

    @Override
    public ReportDTO getReport(String reportId) {
        ReportDTO reportDTO = ReportDTO.toDTO(reportRepository.findById(reportId).get());
        reportDTO.setComplaintCount(complaintRepository.countByReportId(reportDTO.getReportId()));

        return reportDTO;
    }

    @Override
    public String modifyReport(ReportDTO reportDTO) {
        ReportEntity originReport = reportRepository.findById(reportDTO.getReportId()).get();
        ReportEntity newReport = ReportEntity.builder()
                        .title(reportDTO.getTitle())
                        .content(reportDTO.getContent().getBytes(StandardCharsets.UTF_8))
                        .userId(originReport.getUserId())
                        .reportId(originReport.getReportId())
                        .build();

        reportRepository.save(newReport);

        return newReport.getReportId();
    }

    @Override
    public void deleteReport(String reportId) {
        reportRepository.deleteById(reportId);
    }

    @Override
    public List<ReportDTO> getSearchReports(String keyword) {
        List<ReportDTO> reportDTOList = new ArrayList<>();
        List<ReportEntity> reportEntityList = reportRepository.findByTitleContainingOrderByCreateDateDesc(keyword);
        reportEntityList.forEach(entity -> {
            reportDTOList.add(ReportDTO.toDTO(entity));
        });

        return reportDTOList;
    }
}
