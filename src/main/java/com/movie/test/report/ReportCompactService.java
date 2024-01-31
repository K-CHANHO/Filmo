package com.movie.test.report;

import com.movie.test.report.report.dto.ReportDTO;
import com.movie.test.report.report.dto.ReportListSearchDTO;
import com.movie.test.report.report.dto.ReportSimpleDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ReportCompactService {

    String registReport(ReportDTO reportDTO);

    String modifyReport(ReportDTO reportDTO);

    void deleteReport(String reportId);

    ReportDTO getSingleReport(String reportId);

    Slice<ReportSimpleDTO> getReportList(ReportListSearchDTO reportListSearchDTO, Pageable pageable);
    ReportSimpleDTO getSimpleReport(String reportId);
}
