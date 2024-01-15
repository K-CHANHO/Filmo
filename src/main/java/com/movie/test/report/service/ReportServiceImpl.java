package com.movie.test.report.service;

import com.movie.test.complaint.repository.ComplaintRepository;
import com.movie.test.hashtag.repository.TagRepository;
import com.movie.test.report.dto.ReportDTO;
import com.movie.test.report.entity.ReportEntity;
import com.movie.test.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ComplaintRepository complaintRepository;
    private final TagRepository tagRepository;

    @Override
    public String registReport(ReportDTO reportDTO) {

        reportDTO.setReportId(UUID.randomUUID().toString());
        reportRepository.save(ReportDTO.toEntity(reportDTO));

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

        ReportEntity reportEntity = reportRepository.findById(reportId).orElseGet(() -> new ReportEntity());
        if(reportEntity.getReportId() == null) {
            return null;
        }

        ReportDTO reportDTO = ReportDTO.toDTO(reportEntity);
        reportDTO.setComplaintCount(complaintRepository.countByReportId(reportDTO.getReportId()));
        return reportDTO;
    }

    @Override
    public String modifyReport(ReportDTO reportDTO) {
        ReportEntity originReport = reportRepository.findById(reportDTO.getReportId()).get();

        // 감상문 수정
        ReportEntity modifiedReport = originReport.toBuilder()
                                .title(reportDTO.getTitle())
                                .content(reportDTO.getContent().getBytes(StandardCharsets.UTF_8))
                                .movieId(reportDTO.getMovieId() == null ? originReport.getMovieId() : reportDTO.getMovieId())
                                .build();

        reportRepository.save(modifiedReport);

        return modifiedReport.getReportId();
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
