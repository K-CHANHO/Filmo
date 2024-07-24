package com.movie.test.report.report.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReportUpdateDto {

    @Schema(description = "감상문 아이디", hidden = true)
    String reportId;

    @Schema(description = "감상문 제목")
    String title;

    @Schema(description = "감상문 내용")
    String content;

    @Schema(description = "영화 아이디")
    String movieId;

    @Schema(description = "메인 포스터 URL")
    String imageUrl;

    @Schema(description = "해시태그")
    String tagString;

}
