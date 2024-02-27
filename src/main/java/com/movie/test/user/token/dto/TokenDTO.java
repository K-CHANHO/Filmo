package com.movie.test.user.token.dto;

import com.movie.test.user.token.entity.TokenEntity;
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

    private String accessToken;
    private String refreshToken;

    static public TokenEntity toEntity(JwtTokenDTO dto){
        TokenEntity entity = TokenEntity.builder()
                .userId(dto.getUserId())
                .refreshToken(dto.getRefreshToken())
                .build();

        return entity;
    }
}
