package com.movie.test.user.userinfo.controller;

import com.movie.test.user.userinfo.dto.UserDTO;
import com.movie.test.user.userinfo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Tag(name = "유저정보", description = "유저정보 API")
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저정보 요청", description = "회원 정보를 조회합니다.")
    @Parameter(name = "userId", description = "조회할 유저의 id")
    @ApiResponse(responseCode = "200", description = "조회한 회원정보 리턴", content = @Content(schema = @Schema(implementation = UserDTO.class)))
    @GetMapping("/userinfo")
    @ResponseBody
    public ResponseEntity userinfo(UserDTO userDTO, HttpServletRequest request, HttpServletResponse response) {

        UserDTO userinfo = userService.getUserInfo(userDTO.getUserId());

        HashMap<String, Object> userinfoMap = new HashMap<>();
        userinfoMap.put("userinfo", userinfo);
        userinfoMap.put("newToken", request.getAttribute("newToken"));

        return new ResponseEntity(userinfoMap, HttpStatus.OK);
    }
}
