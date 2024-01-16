package com.movie.test.report;

import com.movie.test.report.report.dto.ReportDTO;

public interface ReportCompactService {

    String registReport(ReportDTO reportDTO);

    String modifyReport(ReportDTO reportDTO);

    void deleteReport(String reportId);
}
