package com.movie.test.common.api.tmdbAPI.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Hidden
public class MovieSearchResponseDTO {

    private Integer page;
    private List<MovieInfoDTO> results;
    private Integer total_pages;
    private Integer total_results;

}
