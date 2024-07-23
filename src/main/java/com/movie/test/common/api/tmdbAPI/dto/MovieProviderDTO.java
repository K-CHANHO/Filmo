package com.movie.test.common.api.tmdbAPI.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Hidden
public class MovieProviderDTO {

    private Integer id;
    private Map<Object, Object> results;
    private Object KR;

    public Object getKR(){
        return this.KR = results.get("KR");
    }
}
