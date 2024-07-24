package com.movie.test.report;

import com.movie.test.report.report.dto.ReportDto;
import com.movie.test.report.report.dto.ReportListSearchDTO;
import com.movie.test.report.report.dto.ReportSaveDto;
import com.movie.test.report.report.dto.ReportSimpleDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ReportCompactService {

    String saveReport(ReportSaveDto reportSaveDto);

    String modifyReport(ReportDto reportDTO);

    void deleteReport(String reportId);

    ReportDto getSingleReport(String reportId);

    Slice<ReportSimpleDTO> getReportList(ReportListSearchDTO reportListSearchDTO, Pageable pageable);
    ReportSimpleDTO getSimpleReport(String reportId);
}
