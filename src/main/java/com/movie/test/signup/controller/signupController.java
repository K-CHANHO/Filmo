package com.movie.test.signup.controller;

import com.google.gson.JsonObject;
import com.movie.test.login.controller.loginController;
import com.movie.test.user.dto.userDTO;
import com.movie.test.user.service.userService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원가입", description = "회원가입 API")
@RestController
public class signupController {

    @Autowired
    private userService userService;

    @Autowired
    private loginController loginController;

    /**
     * 회원가입 로직
     * @param userDTO : uid(소셜에서 주는 정보), type, profileURL
     * @return
     */
    @Operation(summary = "회원가입 요청", description = "회원가입을 진행합니다.")
    @Parameters(value = {
            @Parameter(name = "uid", description = "소셜로그인으로 부터 받은 uid", required = true),
            @Parameter(name = "type", description = "소셜로그인 타입(kakao, google, naver)", required = true),
            @Parameter(name = "profileURL", description = "프로필 사진 URL")
    })
    @ApiResponse(responseCode = "200", description = "회원가입 완료 시 가입된 회원정보 리턴", content = @Content(schema = @Schema(implementation = userDTO.class)))
    @PostMapping("/signup")
    public ResponseEntity userCreate(userDTO userDTO) {

        userDTO newUser = userService.newUserSave(userDTO);

        JsonObject userinfo = new JsonObject();
        userinfo.addProperty("uid", newUser.getUid());
        userinfo.addProperty("userid", newUser.getUserId());
        userinfo.addProperty("type", newUser.getType());
        userinfo.addProperty("nickname", newUser.getNickname());
        userinfo.addProperty("profileURL", newUser.getProfileURL());
        userinfo.addProperty("last_login_date", String.valueOf(newUser.getLastLoginDate()));
        userinfo.addProperty("create_date", String.valueOf(newUser.getCreateDate()));

        JsonObject serverData = new JsonObject();
        serverData.add("userinfo", userinfo);


        return new ResponseEntity(serverData, HttpStatus.OK);

    }
}
