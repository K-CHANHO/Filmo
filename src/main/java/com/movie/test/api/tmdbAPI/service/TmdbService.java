package com.movie.test.api.tmdbAPI.service;

import com.movie.test.api.tmdbAPI.dto.MovieSearchApiDTO;

public interface TmdbService {

    Object getMovieDetailInfo(MovieSearchApiDTO searchDTO);
    Object getMovieSearchList(MovieSearchApiDTO searchDTO);
    Object getProviders(String movieId);
    Object getImages(MovieSearchApiDTO movieSearchApiDTO);
    Object getConfiguration();

}
