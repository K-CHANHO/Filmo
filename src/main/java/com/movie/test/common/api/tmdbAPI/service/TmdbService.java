package com.movie.test.common.api.tmdbAPI.service;

import com.movie.test.common.api.tmdbAPI.dto.MovieDetailSearchApiDTO;
import com.movie.test.common.api.tmdbAPI.dto.MovieSearchApiDTO;

public interface TmdbService {

    Object getMovieDetailInfo(MovieDetailSearchApiDTO searchDTO);
    Object getMovieSearchList(MovieSearchApiDTO searchDTO);
    Object getProviders(String movieId);
    Object getImages(MovieDetailSearchApiDTO movieSearchApiDTO);
    Object getConfiguration();
    Object getVideos(MovieDetailSearchApiDTO movieSearchApiDTO);
    Object getCertification(String movieId);
}
