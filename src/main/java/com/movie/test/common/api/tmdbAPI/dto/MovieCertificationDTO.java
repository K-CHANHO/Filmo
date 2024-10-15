package com.movie.test.common.api.tmdbAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieCertificationDTO {
    private Integer id;
    private List<CertificationResult> results;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CertificationResult {
        private String iso_3166_1;
        private List<ReleaseDateResult> release_dates;

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ReleaseDateResult {
            private String certification;
            private Object[] descriptors;
            private String iso_639_1;
            private String note;
            private String release_date;
            private String type;

            public String getCertification() {
                return certification;
            }
        }
    }
}
