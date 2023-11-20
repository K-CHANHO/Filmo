package com.movie.test.tmdbAPI.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.movie.test.tmdbAPI.dto.movieSearchApiDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Slf4j
public class movieSearchServiceImpl implements movieSearchService {

    @Value("${tmdb.api.movie.requestURL}")
    private String TMDB_requestURL;

    @Value("${tmdb.api.key}")
    private String TMDB_key;

    @Value("${tmdb.api.token}")
    private String TMDB_token;



    @Override
    public Object getMovieInfo(movieSearchApiDTO searchDTO) {

        WebClient webClient = WebClient.builder()
                .baseUrl(TMDB_requestURL)
                .defaultHeader("accept", "application/json")
                .defaultHeader("Authorization", "Bearer " + TMDB_token)
                .build()
                ;

        Map<String, String> resultMap = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("query", searchDTO.getQuery())
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        /*
        Object[] objects = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("query", searchDTO.getQuery())
                        .build())
                .retrieve()
                .bodyToMono(Object[].class)
                .block()
                ;
        */

        log.info("{}", resultMap);
//        JsonParser jsonParser = new JsonParser();
//        JsonObject jsonObject = (JsonObject) jsonParser.parse(resultMap);


        return resultMap;

    }
}
