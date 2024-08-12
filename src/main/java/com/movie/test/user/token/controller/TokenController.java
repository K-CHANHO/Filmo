package com.movie.test.user.token.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.movie.test.user.token.dto.JwtTokenDTO;
import com.movie.test.user.token.dto.TokenDTO;
import com.movie.test.user.token.service.JwtTokenProvider;
import com.movie.test.user.token.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "토큰", description = "토큰 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {

    private final TokenService tokenService;
    private final Gson gson;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "토큰 재발급", description = "AccessToken이 만료되었을 경우 RefreshToken을 이용하여 토큰을 재발급합니다.<br>필요값 : AccessToken, RefreshToken")
    @PostMapping("/refresh")
    public ResponseEntity refreshToken(@RequestBody TokenDTO tokenDTO) throws JsonProcessingException {

        JsonObject returnData = new JsonObject();

//        boolean validCheck = tokenService.checkUser(tokenDTO);
        boolean validCheck = jwtTokenProvider.validateToken(tokenDTO.getRefreshToken());
        if(validCheck){
//            String userId = tokenService.resolveUserId(tokenDTO.getAccessToken());
            String userId = tokenService.resolveUserId(tokenDTO.getAccessToken());
            String newAccess = tokenService.makeAccessToken(userId);
            String newRefresh = tokenService.makeRefreshToken();

            // refresh token DB에 저장
            JwtTokenDTO token = JwtTokenDTO.builder()
                    .userId(userId)
                    .refreshToken(newRefresh)
                    .build();
            tokenService.saveRefreshToken(token);

            JsonObject json = new JsonObject();
            json.addProperty("AccessToken", newAccess);
            json.addProperty("RefreshToken", newRefresh);
            returnData.addProperty("value", gson.toJson(json));

            return new ResponseEntity(json, HttpStatus.OK);
        }

        return new ResponseEntity("다시 로그인을 해주세요.", HttpStatus.UNAUTHORIZED);
    }

}
