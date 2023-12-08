package com.movie.test.tmdbAPI.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.movie.test.tmdbAPI.dto.MovieInfoDTO;
import com.movie.test.tmdbAPI.dto.MovieProviderDTO;
import com.movie.test.tmdbAPI.dto.MovieSearchApiDTO;
import com.movie.test.tmdbAPI.dto.MovieSearchResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Map;

@Service
@Slf4j
public class MovieSearchServiceImpl implements MovieSearchService {

    @Value("${tmdb.api.search.movie.baseURL}")
    private String TMDB_baseURL;

    @Value("${tmdb.api.search.providers.requestURL}")
    private String providers_requestURL;

    @Value("${tmdb.api.key}")
    private String TMDB_key;

    @Value("${tmdb.api.token}")
    private String TMDB_token;



    @Override
    public Object getMovieInfo(MovieSearchApiDTO searchDTO) {

        WebClient webClient = WebClient.builder()
                .baseUrl(TMDB_baseURL)
                .defaultHeader("accept", "application/json")
                .defaultHeader("Authorization", "Bearer " + TMDB_token)
                .build()
                ;

        MovieSearchResponseDTO resultDTO = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/3/search/movie")
                        .queryParam("query", searchDTO.getQuery())
                        .queryParam("language", "ko")
                        .build())
                .retrieve()
                .bodyToMono(MovieSearchResponseDTO.class)
                .block();

        // 제공하는 OTT 정보 추가
        ArrayList<MovieInfoDTO> results = resultDTO.getResults();
        for (MovieInfoDTO result : results) {
            Integer movieId = result.getId();


            MovieProviderDTO providers = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/3/movie/" + movieId + "/watch/providers")
                            .build())
                    .retrieve()
                    .bodyToMono(MovieProviderDTO.class)
                    .block();

            result.setProviders(providers.getKR());
        }

        return resultDTO;

    }
}
