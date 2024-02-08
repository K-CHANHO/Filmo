package com.movie.test.user.token.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.movie.test.user.token.dto.TokenDTO;
import com.movie.test.user.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/refresh")
    public ResponseEntity refreshToken(TokenDTO tokenDTO) throws JsonProcessingException {

        tokenService.checkUser(tokenDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

}
