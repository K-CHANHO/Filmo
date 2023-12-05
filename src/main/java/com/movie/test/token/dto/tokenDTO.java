package com.movie.test.token.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Hidden
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class tokenDTO {

    private String uid;
    private String type;
    private String jwt;
    private String userId;
}
