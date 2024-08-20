package com.movie.test.common.api.tmdbAPI.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieSearchApiDTO {

    // 검색어
    private String query;

    @Hidden
    @Builder.Default
    private String language = "ko";

    @Hidden
    @Builder.Default
    private int page = 1;
}
