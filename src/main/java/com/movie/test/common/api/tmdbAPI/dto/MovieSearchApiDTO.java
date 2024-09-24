package com.movie.test.common.api.tmdbAPI.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieSearchApiDTO {

    @Schema(description = "검색어")
    private String query;

    @Hidden
    @Builder.Default
    private String language = "ko";

    @Schema(description = "페이지", defaultValue = "1")
    @Builder.Default
    private int page = 1;
}
