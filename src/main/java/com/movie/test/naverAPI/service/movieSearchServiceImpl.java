package com.movie.test.naverAPI.service;

import com.movie.test.naverAPI.dto.movieSearchApiDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class movieSearchServiceImpl implements movieSearchService {

    @Value("${naver.api.movie.requestURL}")
    private String requestURL;

    @Value("${naver.api.client.id}")
    private String clientId;

    @Value("${naver.api.client.secret}")
    private String clientSecret;



    @Override
    public void getMovieInfo(movieSearchApiDTO searchDTO) {

        WebClient webClient = WebClient.builder()
                .baseUrl(requestURL)
                .build()
                ;

        Map<String, Object> resultMap = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search/movie.json")
                        .queryParams(dtoToMap(searchDTO))
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        log.info(resultMap.toString());


    }
}
