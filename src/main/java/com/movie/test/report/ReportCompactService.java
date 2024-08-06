package com.movie.test.report;

import com.movie.test.report.report.dto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ReportCompactService {

    String saveReport(ReportSaveDto reportSaveDto);

    String updateReport(ReportUpdateDto reportUpdateDto);

    void deleteReport(String reportId);

    ReportDto getSingleReport(String reportId);

    Slice<ReportSimpleDTO> getReportList(ReportSearchDTO reportSearchDTO, Pageable pageable);
    ReportSimpleDTO getSimpleReport(String reportId);
}
