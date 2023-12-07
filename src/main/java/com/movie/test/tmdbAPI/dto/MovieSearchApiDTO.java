package com.movie.test.tmdbAPI.dto;

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

    private String query;
    private Boolean includeAdult;
    private String language;
    private String primaryReleaseYear;
    private Integer page;
    private String region;
    private String year;

}
