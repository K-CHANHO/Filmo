package com.movie.test.report.report.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportSearchDTO {

    @Builder.Default
    @Schema(description = "마지막으로 조회된 감상문 id")
    private String lastReportId="";

    @Schema(description = "검색어")
    private String keyword;

    @Schema(description = "해당 사용자의 감상문 검색")
    private String userId;

}
