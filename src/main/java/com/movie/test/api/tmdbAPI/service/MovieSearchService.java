package com.movie.test.api.tmdbAPI.service;

import com.movie.test.api.tmdbAPI.dto.MovieSearchApiDTO;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public interface MovieSearchService {

    Object getMovieInfo(MovieSearchApiDTO searchDTO);


    default MultiValueMap<String, String> dtoToMap(MovieSearchApiDTO searchDTO) {

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
