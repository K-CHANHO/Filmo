package com.movie.test.report.report.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Hidden
public class ReportListSearchDTO {

    @Builder.Default
    private String lastReportId="";
    private String keyword;

}
