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
public class MovieDetailSearchApiDTO {

    // 영화 상세 정보
    private String movieId;

    @Hidden
    @Builder.Default
    private String language = "ko";

}
