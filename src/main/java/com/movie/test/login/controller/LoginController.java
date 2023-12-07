package com.movie.test.login.controller;

import com.google.gson.JsonObject;
import com.movie.test.token.dto.TokenDTO;
import com.movie.test.token.service.TokenServiceImpl;
import com.movie.test.user.dto.UserDTO;
import com.movie.test.login.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "로그인", description = "소셜로그인을 이용한 로그인 API")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenServiceImpl tokenServiceImpl;

    @Operation(summary = "로그인 요청", description = "소셜로그인으로 부터 받은 정보로 로그인 처리 후 토큰을 발급합니다.")
    @Parameters({
        @Parameter(name = "uid", description = "소셜로그인으로부터 받은 uid", required = true),
        @Parameter(name = "type", description = "소셜 플랫폼, kakao OR google OR naver", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "회원일 경우 로그인 처리(토큰 발급)", headers = @Header(name = "token", description = "로그인 토큰")),
            @ApiResponse(responseCode = "406", description = "비회원일 경우 회원가입 페이지로 이동")
    })
    @PostMapping("/login")
    public ResponseEntity login(UserDTO logingUser, HttpServletResponse response){

        String jwtToken = null;
        UserDTO isExistUser = null;
        JsonObject serverData = new JsonObject();

        isExistUser = loginService.isExistUser(logingUser);
        if(isExistUser != null){
            TokenDTO token = TokenDTO.builder()
                    .uid(isExistUser.getUid())
                    .type(isExistUser.getType())
                    .userId(isExistUser.getUserId())
                    .build();

            jwtToken = tokenServiceImpl.makeJwtToken(token);
            serverData.addProperty("status", "200");
            serverData.addProperty("token", jwtToken);
            response.setHeader("token", jwtToken);
            return new ResponseEntity<>(serverData, HttpStatus.OK);
        } else {
            serverData.addProperty("status", "401");
            return new ResponseEntity<>(serverData, HttpStatus.UNAUTHORIZED);
        }
    }

}
