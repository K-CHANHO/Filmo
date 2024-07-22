package com.movie.test.user.userinfo.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.movie.test.user.token.dto.JwtTokenDTO;
import com.movie.test.user.token.dto.TokenDTO;
import com.movie.test.user.token.service.TokenService;
import com.movie.test.user.userinfo.dto.UserDto;
import com.movie.test.user.userinfo.dto.UserLoginDto;
import com.movie.test.user.userinfo.dto.UserSignupDto;
import com.movie.test.user.userinfo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "유저정보", description = "유저정보 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;
    private final Gson gson;

    /**
     * 회원가입
     */
    @Operation(summary = "회원가입 요청", description = "회원가입을 요청합니다. 필요값 : uid, type, profileUrl")
    @ApiResponse(responseCode = "200", description = "회원가입 성공 시 가입된 회원정보 리턴", content = @Content(schema = @Schema(implementation = UserDto.class)))
    @PostMapping("/signup")
    public ResponseEntity userSignup(@RequestBody UserSignupDto userSignupDto) {

        UserDto signupUser = userService.userSignup(userSignupDto);

        // json 변환 후 리턴
        JsonObject returnData = new JsonObject();
        returnData.addProperty("value", gson.toJson(signupUser));
        returnData.addProperty("status", "200");

        return new ResponseEntity(returnData, HttpStatus.OK);

    }

    /**
     * 로그인
     */
    @Operation(summary = "로그인 요청", description = "소셜로그인으로 부터 받은 정보로 로그인 처리 후 토큰을 발급합니다. 필요값 : uid, type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원일 경우 토큰 발급", content = @Content(schema = @Schema(implementation = TokenDTO.class))),
            @ApiResponse(responseCode = "401", description = "비회원일 경우 401 리턴")
    })
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginDto userLoginDto){
        boolean isExistUser = userService.isExistUser(userLoginDto.getUid(), userLoginDto.getType());

        if(isExistUser) {
            UserDto getUserinfo = userService.getUserInfoByUidAndType(userLoginDto.getUid(), userLoginDto.getType());
            JwtTokenDTO token = userService.login(getUserinfo);
            tokenService.saveRefreshToken(token);

            JsonObject returnData = new JsonObject();
            returnData.addProperty("value", gson.toJson(token));
            returnData.addProperty("status", "200");

            return new ResponseEntity<>(returnData, HttpStatus.OK);
        } else {
            JsonObject returnData = new JsonObject();
            returnData.addProperty("message", "Need to Sign-Up first");
            returnData.addProperty("status", "401");

            return new ResponseEntity<>(returnData, HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 유저정보 조회
     */
    @Operation(summary = "유저정보 요청", description = "회원 정보를 조회합니다.")
    @Parameter(name = "userId", description = "조회할 유저의 id, 빈 값이면 현재 로그인한 사용자의 정보를 조회한다.")
    @ApiResponse(responseCode = "200", description = "조회한 회원정보 리턴", content = @Content(schema = @Schema(implementation = UserDto.class)))
    @GetMapping("/get")
    public ResponseEntity userinfo(String userId, String loginId) {

        if(userId == null || userId.equals("")) {
            userId = loginId;
        }

        UserDto userinfo = userService.getUserInfo(userId);

        JsonObject returnData = new JsonObject();
        returnData.addProperty("value", gson.toJson(userinfo));
        returnData.addProperty("status", "200");

        return new ResponseEntity(returnData, HttpStatus.OK);
    }

    /**
     * 유저 권한 확인
     */
    @Operation(summary = "유저권한 확인", description = "유저의 권한을 확인합니다.")
    @Parameter(name = "userId", description = "조회할 유저의 id", required = true)
    @PostMapping("/roles")
    public ResponseEntity userRoles(UserDto userDTO) {

        List<String> roles = userService.checkUserRoles(userDTO.getUserId());

        return new ResponseEntity(roles, HttpStatus.OK);
    }

    /**
     * 유저정보 수정
     */
    @PostMapping("/update")
    public ResponseEntity updateUserInfo(UserDto userDTO){

        UserDto modifiedUserinfo = userService.updateUserinfo(userDTO);

        return new ResponseEntity(modifiedUserinfo, HttpStatus.OK);

    }
}
