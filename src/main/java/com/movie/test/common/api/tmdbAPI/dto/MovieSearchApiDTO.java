package com.movie.test.common.api.tmdbAPI.dto;

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

    @Builder.Default
    private String language = "ko";

    @Builder.Default
    private int page = 1;

    // 영화 상세 정보
    private String movieId;



}
