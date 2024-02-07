package com.movie.test.user.token.dto;

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
public class TokenDTO {

    private String uid;
    private String type;
    private String userId;

    private String jwt;
    private String accessToken;
    private String refreshToken;
}
