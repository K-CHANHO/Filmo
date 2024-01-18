package com.movie.test.report.report.repository;

import com.movie.test.report.report.dto.ReportListSearchDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ReportRepositoryCustom {

    Slice<String> getReportListId(ReportListSearchDTO reportListSearchDTO, Pageable pageable);
}
