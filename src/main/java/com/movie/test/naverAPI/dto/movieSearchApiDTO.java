package com.movie.test.naverAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class movieSearchApiDTO {

    private String query;
    private Integer display;
    private Integer start;
    private String genre;
    private String country;
    private Integer yearfrom;
    private Integer yearto;


}
