package com.movie.test.report.report.service;

import com.movie.test.common.cef.UUIDCustom;
import com.movie.test.report.report.dto.ReportDto;
import com.movie.test.report.report.dto.ReportListSearchDTO;
import com.movie.test.report.report.dto.ReportSaveDto;
import com.movie.test.report.report.dto.ReportUpdateDto;
import com.movie.test.report.report.entity.ReportEntity;
import com.movie.test.report.report.mapper.ReportSaveMapper;
import com.movie.test.report.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public String saveReport(ReportSaveDto reportSaveDto) {

        ReportDto reportDTO = ReportSaveMapper.INSTANCE.toReportDto(reportSaveDto);

        reportDTO.setReportId(UUIDCustom.createUUID());
        ReportEntity saved = reportRepository.save(ReportDto.toEntity(reportDTO));

        return saved.getReportId();
    }

    @Override
    public ReportDto getReport(String reportId) {

        ReportEntity reportEntity = reportRepository.findById(reportId).orElseThrow();

        return ReportDto.toDto(reportEntity);
    }

    @Override
    public String updateReport(ReportUpdateDto reportUpdateDto) {
        ReportEntity originReport = reportRepository.findById(reportUpdateDto.getReportId()).orElseThrow();

        // 감상문 수정
        ReportEntity updateReport = originReport.toBuilder()
                                .title(reportUpdateDto.getTitle())
                                .content(reportUpdateDto.getContent())
                                .movieId(reportUpdateDto.getMovieId() == null ? originReport.getMovieId() : reportUpdateDto.getMovieId())
                                .imageUrl(reportUpdateDto.getImageUrl())
                                .build();

        reportRepository.save(updateReport);

        return updateReport.getReportId();
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

        if(reportId == null || "".equals(reportId) || reportRepository.findById(reportId).isEmpty()) return false;

        return true;
    }
}
