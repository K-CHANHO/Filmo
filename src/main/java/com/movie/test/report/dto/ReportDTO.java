package com.movie.test.report.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Hidden
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {

    private String reportId;
    private String title;
    private String content;
    private String userId;

    private Timestamp createDate;
    private Timestamp lastModifiedDate;

    private long complaintCount; // 신고 횟수
}
