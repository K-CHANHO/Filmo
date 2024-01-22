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

        private Integer id; // 영화 id
        private String title; // 영화 제목
        private String poster_path; // 포스터
        private Object providers; // OTT 정보
        private String release_date; // 개봉날짜
        private Integer[] genre_ids; // 장르
        private String original_language; // 국가?
        private String overview; // 줄거리

        /*
        관람등급은 없음.
        private boolean adult;
        private String backdrop_path;
        private String original_title;
        private Number popularity;
        private boolean video;
        private Number vote_average;
        private Integer vote_count;
        private String videoURL;
        private String[] images;
         */

}
