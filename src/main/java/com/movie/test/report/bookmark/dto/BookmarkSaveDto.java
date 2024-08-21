package com.movie.test.report.bookmark.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookmarkSaveDto {

    @Schema(description = "북마크하려는 감상문 아이디")
    private String reportId;

    @Hidden
    private String userId;
}
