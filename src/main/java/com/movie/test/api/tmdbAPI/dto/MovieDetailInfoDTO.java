package com.movie.test.api.tmdbAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetailInfoDTO {

    private boolean adult;
    private String backdrop_path;
    private String belongs_to_collection;
    private Integer budget;
    private Object[] genres;
    private Integer id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private Number popularity;
    private String poster_path;
    private Object[] production_companies;
    private Object[] production_countries;
    private String release_date;
    private Integer revenue;
    private Integer runtime;
    private Object[] spoken_languages;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private Number vote_average;
    private Integer vote_count;

}
