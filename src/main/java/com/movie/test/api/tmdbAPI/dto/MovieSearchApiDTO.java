package com.movie.test.api.tmdbAPI.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Hidden
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieSearchApiDTO {

    // 영화 검색 리스트
    private String query;
    private Boolean includeAdult = false;
    private String language = "ko";
    private String primaryReleaseYear;
    private int page = 1;
    private String region;
    private String year;

    // 영화 상세 정보
    private int movieId;

}
