package com.movie.test.common.api.tmdbAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieVideoDTO {

    private Integer id;
    private List<result> results;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class result {
        private String name;
        private String key;
        private String site;
    }
}
