package com.movie.test.report.report.service;

import com.movie.test.report.report.dto.ReportDTO;
import com.movie.test.report.report.dto.ReportListSearchDTO;
import com.movie.test.report.report.entity.ReportEntity;
import com.movie.test.report.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public String registReport(ReportDTO reportDTO) {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(System.currentTimeMillis()));
        stringBuilder.append(UUID.randomUUID().toString());

        reportDTO.setReportId(stringBuilder.toString());
        ReportEntity saved = reportRepository.save(ReportDTO.toEntity(reportDTO));

        return saved.getReportId();
    }

    @Override
    public ReportDTO getReport(String reportId) {

        ReportEntity reportEntity = reportRepository.findById(reportId).orElseGet(() -> new ReportEntity());
        if(reportEntity.getReportId() == null) {
            return null;
        }

        return ReportDTO.toDTO(reportEntity);
    }

    @Override
    public String modifyReport(ReportDTO reportDTO) {
        ReportEntity originReport = reportRepository.findById(reportDTO.getReportId()).get();

        // 감상문 수정
        ReportEntity modifiedReport = originReport.toBuilder()
                                .title(reportDTO.getTitle())
                                .content(reportDTO.getContent())
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
    public Slice<String> getSearchReportId(ReportListSearchDTO reportListSearchDTO, Pageable pageable) {

        return reportRepository.getReportListId(reportListSearchDTO, pageable);
    }

    @Override
    public Long getSearchReportCount(ReportListSearchDTO reportListSearchDTO) {
        return reportRepository.getReportSearchCount(reportListSearchDTO);
    }

    @Override
    public List<String> getReportIdByUserId(String userId) {
        return reportRepository.getReportIdByUserId(userId);
    }

    @Override
    public boolean validationReportId(String reportId) {

        if(reportId == null || "".equals(reportId) || reportRepository.findById(reportId) == null) return false;

        return true;
    }
}
