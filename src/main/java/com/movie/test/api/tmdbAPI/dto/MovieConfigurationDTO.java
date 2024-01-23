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
public class MovieConfigurationDTO {

    private Image images;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Image {
        private String base_url;
        private String secure_base_url;
        private List<String> backdrop_sizes;
        private List<String> logo_sizes;
        private List<String> poster_sizes;
        private List<String> profile_sizes;
        private List<String> still_sizes;
    }
}
