package com.movie.test.report.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReportSearchReturnDto {

    private Long searchReportCount;
    private List<ReportSimpleDTO> searchReport;
    private boolean hasNext;
}
