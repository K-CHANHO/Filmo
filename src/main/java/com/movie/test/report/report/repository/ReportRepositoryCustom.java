package com.movie.test.report.report.repository;

import com.movie.test.report.report.dto.ReportSearchDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ReportRepositoryCustom {

    Slice<String> getReportListId(ReportSearchDTO reportSearchDTO, Pageable pageable);

    Long getReportSearchCount(ReportSearchDTO reportSearchDTO);

    List<String> getReportIdByUserId(String userId);
}
