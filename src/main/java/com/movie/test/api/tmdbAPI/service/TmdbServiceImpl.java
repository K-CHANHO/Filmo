package com.movie.test.api.tmdbAPI.service;

import com.movie.test.api.tmdbAPI.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

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

        // 제공하는 OTT 정보 추가 TODO : 따로 빼기
        List<MovieInfoDTO> list = resultDTO.getResults().stream().map(result -> {
            Integer movieId = result.getId();

            MovieProviderDTO providers = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/3/movie/" + movieId + "/watch/providers")
                            .build())
                    .retrieve()
                    .bodyToMono(MovieProviderDTO.class)
                    .block();

            result.setProviders(providers.getKR());

            return result;
        }).toList();
        resultDTO.setResults(list);

//        for (MovieInfoDTO result : resultDTO.getResults()) {
//            Integer movieId = result.getId();
//
//            MovieProviderDTO providers = webClient.get()
//                    .uri(uriBuilder -> uriBuilder
//                            .path("/3/movie/" + movieId + "/watch/providers")
//                            .build())
//                    .retrieve()
//                    .bodyToMono(MovieProviderDTO.class)
//                    .block();
//
//            result.setProviders(providers.getKR());
//        }

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
}
