package com.movie.test.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class reportDTO {

    private String reportId;
    private String title;
    private String content;
    private String userid;

    private Timestamp create_date;
    private Timestamp update_date;
}
