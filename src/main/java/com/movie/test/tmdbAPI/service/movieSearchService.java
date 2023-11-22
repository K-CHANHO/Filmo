package com.movie.test.tmdbAPI.service;

import com.google.gson.JsonObject;
import com.movie.test.tmdbAPI.dto.movieSearchApiDTO;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public interface movieSearchService {

    Object getMovieInfo(movieSearchApiDTO searchDTO);



    default MultiValueMap<String, String> dtoToMap(movieSearchApiDTO searchDTO) {

        MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<>();

        queryMap.add("query", searchDTO.getQuery());
        queryMap.add("include_adult", searchDTO.getIncludeAdult() != null? searchDTO.getIncludeAdult().toString() : null);
        queryMap.add("language", searchDTO.getLanguage());
        queryMap.add("primary_release_year", searchDTO.getPrimaryReleaseYear());
        queryMap.add("page", searchDTO.getPage() != null? searchDTO.getPage().toString() : null);
        queryMap.add("region", searchDTO.getRegion());
        queryMap.add("year", searchDTO.getYear());

        return queryMap;
    }
}
