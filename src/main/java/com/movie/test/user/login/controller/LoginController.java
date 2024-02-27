package com.movie.test.user.login.controller;

import com.movie.test.user.token.dto.JwtTokenDTO;
import com.movie.test.user.token.dto.TokenDTO;
import com.movie.test.user.token.service.JwtTokenProvider;
import com.movie.test.user.token.service.TokenService;
import com.movie.test.user.userinfo.dto.UserDTO;
import com.movie.test.user.userinfo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "로그인", description = "소셜로그인을 이용한 로그인 API")
@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final TokenService tokenService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "로그인 요청", description = "소셜로그인으로 부터 받은 정보로 로그인 처리 후 토큰을 발급합니다.")
    @Parameters({
        @Parameter(name = "uid", description = "소셜로그인으로부터 받은 uid", required = true),
        @Parameter(name = "type", description = "소셜 플랫폼, kakao OR google OR naver", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "회원일 경우 로그인 처리(토큰 발급)", headers = @Header(name = "Authorization", description = "토큰")),
            @ApiResponse(responseCode = "401", description = "비회원일 경우 401 에러 리턴")
    })
    @PostMapping("/login")
    public ResponseEntity login(UserDTO logingUser){

        log.info("login 요청 : {}", logingUser.toString());

        UserDTO getUserinfo = userService.getUserInfoByUidAndType(logingUser.getUid(), logingUser.getType());

        // 회원정보가 있는 경우
        if(getUserinfo != null){

            JwtTokenDTO token = userService.login(getUserinfo);
            tokenService.saveRefreshToken(token);
//            String accessToken = tokenService.makeAccessToken(getUserinfo.getUserId());
//            String refreshToken = tokenService.makeRefreshToken();
//            TokenDTO token = TokenDTO.builder()
//                    .accessToken(accessToken)
//                    .refreshToken(refreshToken)
//                    .userId(getUserinfo.getUserId())
//                    .build();
//            tokenService.saveRefreshToken(token);

            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        // 회원정보가 없는 경우
        else {
            return new ResponseEntity<>("Need to Sign-Up first", HttpStatus.UNAUTHORIZED);
        }

    }

}
