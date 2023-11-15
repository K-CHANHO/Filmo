package com.movie.test.naverAPI.service;

import com.movie.test.naverAPI.dto.movieSearchApiDTO;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

public interface movieSearchService {

    void getMovieInfo(movieSearchApiDTO searchDTO);



    default MultiValueMap<String, String> dtoToMap(movieSearchApiDTO searchDTO) {

        MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<>();

        queryMap.add("query", searchDTO.getQuery());
        queryMap.add("display", searchDTO.getDisplay().toString());
        queryMap.add("start", searchDTO.getStart().toString());
        queryMap.add("genre", searchDTO.getGenre());
        queryMap.add("country", searchDTO.getCountry());
        queryMap.add("yearfrom", searchDTO.getYearfrom().toString());
        queryMap.add("yearto", searchDTO.getYearto().toString());

        return queryMap;
    }
}
