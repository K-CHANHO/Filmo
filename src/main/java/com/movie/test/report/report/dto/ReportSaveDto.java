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
public class ReportSaveDto {

    @Schema(description = "감상문 제목")
    String title;
    @Schema(description = "감상문 내용")
    String content;
    @Schema(description = "영화 아이디")
    String movieId;
    @Schema(description = "해시태그")
    String tagString;

}
