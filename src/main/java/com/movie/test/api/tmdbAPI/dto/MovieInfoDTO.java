package com.movie.test.api.tmdbAPI.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Hidden
public class MovieInfoDTO {

        private boolean adult;
        private String backdrop_path;
        private Integer[] genre_ids;
        private Integer id;
        private String original_language;
        private String original_title;
        private String overview;
        private Number popularity;
        private String poster_path;
        private String release_date;
        private String title;
        private boolean video;
        private Number vote_average;
        private Integer vote_count;

        private Object providers;

}
