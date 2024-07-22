package com.movie.test.user.token.dto;

import com.movie.test.user.token.entity.TokenEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDTO {

    @Schema(description = "소셜로그인 uid")
    private String uid;
    @Schema(description = "소셜로그인 종류")
    private String type;
    @Schema(description = "유저 고유아이디")
    private String userId;

    @Schema(description = "Access Token. 요청 시 필요")
    private String accessToken;
    @Schema(description = "Refresh Token. Access Token 만료 시 필요")
    private String refreshToken;

    static public TokenEntity toEntity(JwtTokenDTO dto){
        TokenEntity entity = TokenEntity.builder()
                .userId(dto.getUserId())
                .refreshToken(dto.getRefreshToken())
                .build();

        return entity;
    }
}
