package com.movie.test.common.api.tmdbAPI.service;

import com.movie.test.common.api.tmdbAPI.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
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
    public Object getMovieDetailInfo(MovieDetailSearchApiDTO searchDTO) {

        WebClient webClient = getWebClient();

        MovieDetailInfoDTO resultDTO = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/3/movie/" + searchDTO.getMovieId())
                        .queryParam("language", searchDTO.getLanguage())
                        .build())
                .retrieve()
                .bodyToMono(MovieDetailInfoDTO.class)
                .block();

        String certification = getCertification(searchDTO.getMovieId());
        resultDTO.setCertification(certification);
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
    public Object getImages(MovieDetailSearchApiDTO movieSearchApiDTO) {

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
    public Object getVideos(MovieDetailSearchApiDTO movieSearchApiDTO) {

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

    @Override
    public String getCertification(String movieId) {
        WebClient webClient = getWebClient();
        MovieCertificationDTO certificationDTO = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/3/movie/"+ movieId +"/release_dates")
                        .build())
                .retrieve()
                .bodyToMono(MovieCertificationDTO.class)
                .block();

        return Objects.requireNonNull(certificationDTO).getResults().stream()
                .filter(result -> "KR".equals(result.getIso_3166_1()))
                .flatMap(result -> result.getRelease_dates().stream()
                        .map(MovieCertificationDTO.CertificationResult.ReleaseDateResult::getCertification))
                .filter(certification -> certification != null && !certification.isEmpty())
                .findFirst()
                .or(() -> certificationDTO.getResults().stream()
                        .filter(result -> "US".equals(result.getIso_3166_1()))
                        .flatMap(result -> result.getRelease_dates().stream()
                                .map(MovieCertificationDTO.CertificationResult.ReleaseDateResult::getCertification))
                        .filter(certification -> certification != null && !certification.isEmpty())
                        .findFirst())
                .orElse("No certification available");
    }
}
