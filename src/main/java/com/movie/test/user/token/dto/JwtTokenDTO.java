package com.movie.test.user.token.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Hidden
public class JwtTokenDTO {
    private String grantType; // Bearer
    private String accessToken;
    private String refreshToken;
    private String userId;
}
