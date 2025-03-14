package com.movie.test.user.userinfo.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.movie.test.report.ReportCompactService;
import com.movie.test.user.token.dto.JwtTokenDTO;
import com.movie.test.user.token.dto.TokenDTO;
import com.movie.test.user.token.service.TokenService;
import com.movie.test.user.userinfo.dto.*;
import com.movie.test.user.userinfo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "유저정보", description = "유저정보 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;
    private final Gson gson;
    private final ReportCompactService reportCompactService;

    /**
     * 회원가입
     */
    @Operation(summary = "회원가입 요청", description = "회원가입을 요청합니다. 필요값 : uid, type, profileUrl")
    @ApiResponse(responseCode = "200", description = "회원가입 성공 시 가입된 회원정보 리턴", content = @Content(schema = @Schema(implementation = UserDto.class)))
    @PreAuthorize("isAnonymous()")
    @PostMapping("/signup")
    public ResponseEntity saveUser(@RequestBody UserSignupDto userSignupDto) {

        UserDto signupUser = userService.saveUser(userSignupDto);

        if(signupUser.getUserId() == null){
            JsonObject returnData = new JsonObject();
            returnData.addProperty("msg", "이미 가입된 이메일입니다. 가입한 플랫폼으로 로그인해주세요.");
            returnData.addProperty("email", signupUser.getEmail());
            returnData.addProperty("type", signupUser.getType());
            returnData.addProperty("dup", "Y");
            return new ResponseEntity(returnData, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity(signupUser, HttpStatus.OK);

    }

    /**
     * 로그인
     */
    @Operation(summary = "로그인 요청", description = "소셜로그인으로 부터 받은 정보로 로그인 처리 후 토큰을 발급합니다. 필요값 : uid, type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원일 경우 토큰 발급", content = @Content(schema = @Schema(implementation = TokenDTO.class))),
            @ApiResponse(responseCode = "401", description = "비회원일 경우 401 리턴")
    })
    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody UserLoginDto userLoginDto){
        Boolean isExistUser = userService.isExistUser(userLoginDto.getEmail(), userLoginDto.getType());


        if(isExistUser) { // 존재하는 유저인 경우 토큰 발급
            UserDto getUserinfo = userService.getUserInfoByUidAndType(userLoginDto.getEmail(), userLoginDto.getType());
            JwtTokenDTO token = userService.loginUser(getUserinfo);
            tokenService.saveRefreshToken(token);

            return new ResponseEntity<>(token, HttpStatus.OK);

        } else { // 존재하지 않는 유저일 경우 401 에러 리턴
            JsonObject returnData = new JsonObject();
            returnData.addProperty("message", "Need to Sign-Up first");
            returnData.addProperty("status", "401");

            return new ResponseEntity<>(returnData, HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 유저정보 조회
     */
    @Operation(summary = "유저정보 조회", description = "회원 정보를 조회합니다.")
    @Parameters({
        @Parameter(name = "userId", description = "조회할 유저의 id, 빈 값이면 현재 로그인한 사용자의 정보를 조회한다."),
    })
    @ApiResponse(responseCode = "200", description = "조회한 회원정보 리턴", content = @Content(schema = @Schema(implementation = UserDto.class)))
    @GetMapping("/get")
    public ResponseEntity getUserInfo(UserIdDto userIdDto, @AuthenticationPrincipal CustomUser user) {


        // userId가 빈 값일 경우 현재 로그인한 사용자의 ID로 대체
        String userId = userIdDto.getUserId();
        if(userId == null || userId.trim().equals("")) {
                userId = user.getUserId();
        }

        userId = userId.trim();
        UserDto userinfo = userService.getUserInfo(userId);

        return new ResponseEntity(userinfo, HttpStatus.OK);
    }

    /**
     * 유저 권한 확인
     */
    @Operation(summary = "유저권한 확인", description = "유저의 권한을 확인합니다.")
    @Parameters({
            @Parameter(name = "userId", description = "조회할 유저의 id, 빈 값일 경우 현재 로그인한 사용자의 권한을 확인합니다."),
    })
    @ApiResponse(responseCode = "200", description = "유저의 권한 리턴")
    @GetMapping("/roles")
    public ResponseEntity getUserRoles(UserIdDto userIdDto, @AuthenticationPrincipal CustomUser user) {

        String userId = userIdDto.getUserId();
        // userId가 빈 값일 경우 현재 로그인한 사용자의 ID로 대체
        if(userId == null || userId.equals("")) {
            userId = userIdDto.getUserId();
        }

        userId = userId.trim();
        List<String> roles = userService.getUserRoles(userId);

        return new ResponseEntity(roles, HttpStatus.OK);
    }

    /**
     * 유저정보 수정
     */
    @Operation(summary = "유저정보 수정", description = "유저의 정보를 수정합니다.")
    @Parameter(name = "loginId", description = "현재 로그인한 유저의 아이디", hidden = true)
    @ApiResponse(responseCode = "200", description = "수정된 유저정보 리턴", content = @Content(schema = @Schema(implementation = UserDto.class)))
    @PostMapping("/update")
    public ResponseEntity updateUserInfo(@RequestBody UserInfoModifyDto userInfoModifyDto, String loginId){

        UserDto modifiedUserinfo = userService.updateUserinfo(userInfoModifyDto, loginId);

        return new ResponseEntity(modifiedUserinfo, HttpStatus.OK);

    }

    /**
     * 회원탈퇴
     */
    @Operation(summary = "회원탈퇴", description = "회원탈퇴 및 모든 데이터 삭제")
    @Parameters({
            @Parameter(name = "userId", description = "탈퇴할 유저의 id", required = true, in = ParameterIn.PATH)
    })
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId, @AuthenticationPrincipal CustomUser user){

        JsonObject returnData = new JsonObject();

        if(userId.equals(user.getUserId())) {
            //reportCompactService.deleteReportByUserId(user.getUserId());
            UserDeleteDto userDeleteDto = UserDeleteDto.builder().userId(userId).build();
            userService.deleteUser(userDeleteDto, user);

            returnData.addProperty("success", true);
            return new ResponseEntity(returnData, HttpStatus.OK);
        } else {

            returnData.addProperty("success", false);
            return new ResponseEntity(returnData, HttpStatus.FORBIDDEN);
        }

    }
}
