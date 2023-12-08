package com.movie.test.hashtag.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Hidden
public class TagInReportDTO {

    private String reportId;
    private Long tagId;
}
