package com.movie.test.follow.controller;

import com.movie.test.follow.dto.FollowDTO;
import com.movie.test.follow.service.FollowService;
import com.movie.test.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "팔로우", description = "팔로우 관련 API")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final UserService userService;

    @Operation(summary = "팔로잉 등록", description = "팔로잉을 등록합니다.")
    @Parameters({
            @Parameter(name = "userId", description = "현재 사용자 id", required = true),
            @Parameter(name = "followTarget", description = "팔로잉할 상대 id", required = true),
    })
    @ApiResponse(responseCode = "200", description = "등록된 팔로잉 정보 리턴")
    @PostMapping("/follow/regist")
    public ResponseEntity registFollow(FollowDTO followDTO) {

        FollowDTO followingResult = followService.registFollowing(followDTO);

        // TODO : 닉네임을 같이 넘겨주는게 좋으려나?
        String nickname = userService.getUserInfo(followingResult.getFollowTarget()).getNickname();
        String msg = nickname + "님을 팔로우하였습니다.";

        return new ResponseEntity(followingResult, HttpStatus.OK);
    }

    @Operation(summary = "팔로잉 취소", description = "팔로잉을 취소합니다.")
    @Parameter(name = "followId", description = "팔로우 id", required = true)
    @ApiResponse(responseCode = "200")
    @PostMapping("/follow/cancle")
    public ResponseEntity cancleFollow(String followId){

        followService.cancleFollow(followId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
