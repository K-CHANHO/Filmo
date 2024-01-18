package com.movie.test.report.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportListSearchDTO {

    @Builder.Default
    private String lastReportId="";
    private String keyword;

}
