package com.example.playWorld.token.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenDTO {
    private String grantType; // Bearer
    private String accessToken;
    private String refreshToken;
//    private Long refreshTokenId;
}
