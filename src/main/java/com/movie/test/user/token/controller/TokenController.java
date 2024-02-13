package com.movie.test.user.token.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.movie.test.user.token.dto.TokenDTO;
import com.movie.test.user.token.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "토큰", description = "토큰 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {

    private final TokenService tokenService;

    @Operation(summary = "토큰 재발급", description = "AccessToken이 만료되었을 경우 RefreshToken을 이용하여 토큰을 재발급합니다.")
    @Parameters({
        @Parameter(name = "accessToken", description = "액세스 토큰", required = true),
        @Parameter(name = "refreshToken", description = "레프레쉬 토큰", required = true)
    })
    @PostMapping("/refresh")
    public ResponseEntity refreshToken(TokenDTO tokenDTO) throws JsonProcessingException {

        boolean validCheck = tokenService.checkUser(tokenDTO);

        if(validCheck){
            String userId = tokenService.resolveUserId(tokenDTO.getAccessToken());
            String newAccess = tokenService.makeAccessToken(userId);
            String newRefresh = tokenService.makeRefreshToken();

            TokenDTO token = TokenDTO.builder()
                    .accessToken(newAccess)
                    .refreshToken(newRefresh)
                    .build();

            return new ResponseEntity(token, HttpStatus.OK);
        }

        return new ResponseEntity("다시 로그인을 해주세요.", HttpStatus.UNAUTHORIZED);
    }

}
