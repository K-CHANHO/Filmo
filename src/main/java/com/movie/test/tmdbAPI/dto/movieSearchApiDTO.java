package com.movie.test.tmdbAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class movieSearchApiDTO {

    private String query;
    private Boolean include_adult;
    private String language;
    private String primary_release_year;
    private Integer page;
    private String region;
    private String year;

}
