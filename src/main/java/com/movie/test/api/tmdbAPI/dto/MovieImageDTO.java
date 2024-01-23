package com.movie.test.api.tmdbAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieImageDTO {

    private Integer id;
    private List<Backdrop> backdrops;
    private List<Logo> logos;
    private List<Poster> posters;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private static class Poster {
        private Number aspect_ratio;
        private Integer height;
        private Integer width;
        private String file_path;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private static class Logo {
        private Number aspect_ratio;
        private Integer height;
        private Integer width;
        private String file_path;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private static class Backdrop {
        private Number aspect_ratio;
        private Integer height;
        private Integer width;
        private String file_path;
    }
}
