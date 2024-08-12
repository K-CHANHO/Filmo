package com.movie.test.common.api.tmdbAPI.service;

import com.movie.test.common.api.tmdbAPI.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class TmdbServiceImpl implements TmdbService {

    @Value("${tmdb.api.search.movie.baseURL}")
    private String TMDB_baseURL;

    @Value("${tmdb.api.key}")
    private String TMDB_key;

    @Value("${tmdb.api.token}")
    private String TMDB_token;

    private WebClient getWebClient(){
        WebClient webClient = WebClient.builder()
                .baseUrl(TMDB_baseURL)
                .defaultHeader("accept", "application/json")
                .defaultHeader("Authorization", "Bearer " + TMDB_token)
                .build();

        return webClient;
    }

    @Override
    public Object getMovieSearchList(MovieSearchApiDTO searchDTO) {

        WebClient webClient = getWebClient();

        MovieSearchResponseDTO resultDTO = webClient.get()
        .uri(uriBuilder -> uriBuilder
                .path("/3/search/movie")
                .queryParam("query", searchDTO.getQuery())
                .queryParam("language", searchDTO.getLanguage())
                .queryParam("page", searchDTO.getPage())
                .build())
        .retrieve()
        .bodyToMono(MovieSearchResponseDTO.class)
        .block();

        return resultDTO;

    }

    @Override
    public Object getMovieDetailInfo(MovieSearchApiDTO searchDTO) {

        WebClient webClient = getWebClient();

        MovieDetailInfoDTO resultDTO = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/3/movie/" + searchDTO.getMovieId())
                        .queryParam("language", searchDTO.getLanguage())
                        .build())
                .retrieve()
                .bodyToMono(MovieDetailInfoDTO.class)
                .block();

        return resultDTO;
    }

    @Override
    public Object getProviders(String movieId) {

        WebClient webClient = getWebClient();

        MovieProviderDTO providers = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/3/movie/" + movieId + "/watch/providers")
                        .build())
                .retrieve()
                .bodyToMono(MovieProviderDTO.class)
                .block();



        return providers.getKR();
    }

    @Override
    public Object getImages(MovieSearchApiDTO movieSearchApiDTO) {

        WebClient webClient = getWebClient();

        MovieImageDTO images = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/3/movie/" + movieSearchApiDTO.getMovieId() + "/images")
//                        .queryParam("language", movieSearchApiDTO.getLanguage())
                        .build())
                .retrieve()
                .bodyToMono(MovieImageDTO.class)
                .block();

        return images;
    }

    @Override
    public Object getConfiguration() {

        WebClient webClient = getWebClient();

        MovieConfigurationDTO configuration = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/3/configuration")
                        .build())
                .retrieve()
                .bodyToMono(MovieConfigurationDTO.class)
                .block();

        return configuration;
    }

    @Override
    public Object getVideos(MovieSearchApiDTO movieSearchApiDTO) {

        WebClient webClient = getWebClient();

        MovieVideoDTO video = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/3/movie/" + movieSearchApiDTO.getMovieId() + "/videos")
                        .queryParam("language", movieSearchApiDTO.getLanguage())
                        .build())
                .retrieve()
                .bodyToMono(MovieVideoDTO.class)
                .block();


        return video;
    }
}
