package com.movie.test.user.follow.controller;

import com.movie.test.user.follow.dto.FollowDeleteDto;
import com.movie.test.user.follow.dto.FollowListSearchDTO;
import com.movie.test.user.follow.dto.FollowSaveDto;
import com.movie.test.user.follow.service.FollowService;
import com.movie.test.user.userinfo.dto.CustomUser;
import com.movie.test.user.userinfo.dto.UserDto;
import com.movie.test.user.userinfo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/follow")
@Tag(name = "팔로우", description = "팔로우 관련 API")
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasRole('ROLE_USER')")
public class FollowController {

    private final FollowService followService;
    private final UserService userService;

    @Operation(summary = "팔로잉 등록", description = "팔로잉을 등록합니다.")
    @ApiResponse(responseCode = "200", description = "등록된 팔로잉 정보 리턴")
    @PostMapping("/save")
    public ResponseEntity saveFollow(FollowSaveDto followSaveDto, @AuthenticationPrincipal CustomUser user) {

        followSaveDto.setUserId(user.getUserId());
        FollowSaveDto returnData = followService.saveFollow(followSaveDto);

        return new ResponseEntity(returnData, HttpStatus.OK);
    }

    @Operation(summary = "팔로잉 취소", description = "팔로잉을 취소합니다.")
    @ApiResponse(responseCode = "200")
    @DeleteMapping("/cancle")
    public ResponseEntity cancleFollow(FollowDeleteDto followDeleteDto, @AuthenticationPrincipal CustomUser user){

        followService.cancleFollow(followDeleteDto.getFollowId(), user.getUserId());

        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "팔로잉 목록", description = "팔로잉 목록을 조회합니다.")
    @Parameters({
        @Parameter(name = "lastUserId", description = "마지막으로 조회된 유저 id, 처음엔 NULL"),
        @Parameter(name = "keyword", description = "검색어"),
    })
    @ApiResponse(responseCode = "200", description = "팔로잉 목록 리턴")
    @GetMapping("/followingList")
    public ResponseEntity getFollowingList(FollowListSearchDTO followListSearchDTO, @Parameter(hidden = true) Pageable pageable){

        // Slice로 구현 : List를 먼저 구하고 그 안에서 Slice로 자르기.
        Slice<UserDto> followingUserInfo = followService.getFollowingUserInfo(followListSearchDTO, pageable);

        Map<String, Object> resultData = new HashMap<>();
        resultData.put("followingUserInfoList", followingUserInfo.getContent());
        resultData.put("hasNext", followingUserInfo.hasNext());

        return new ResponseEntity(resultData, HttpStatus.OK);
    }

    @Operation(summary = "팔로워 목록", description = "팔로워(나를 팔로잉 하는 사람) 목록을 조회합니다.")
    @Parameters({
            @Parameter(name = "userId", description = "조회할 유저의 id, Null인 경우 로그인한 사용자의 팔로워 조회", required = false),
            @Parameter(name = "lastUserId", description = "마지막으로 조회된 유저 id", required = false),
            @Parameter(name = "keyword", description = "검색어")
    })
    @ApiResponse(responseCode = "200", description = "팔로워 목록 리턴")
    @GetMapping("/followerList")
    public ResponseEntity getFollowerList(FollowListSearchDTO followListSearchDTO,  @Parameter(hidden = true) Pageable pageable){

        // Slice로 구현 : List를 먼저 구하고 그 안에서 Slice로 자르기.
        Slice<UserDto> followerUserInfo = followService.getFollowerUserInfo(followListSearchDTO, pageable);

        Map<String, Object> resultData = new HashMap<>();
        resultData.put("followerUserInfoList", followerUserInfo.getContent());
        resultData.put("hasNext", followerUserInfo.hasNext());

        return new ResponseEntity(resultData, HttpStatus.OK);
    }

    @Operation(summary = "팔로잉 확인", description = "상대를 팔로잉하고 있는 지 확인")
    @Parameters({
            @Parameter(name = "followTarget", description = "상대 id", required = true)
    })
    @ApiResponse(responseCode = "200", description = "팔로잉하고 있는 경우 true, 아닌 경우 false 리턴")
    @GetMapping("/isFollow")
    public ResponseEntity isFollowing(@Parameter(hidden = true)String userId, String followTarget){

        boolean isFollowing = followService.isFollowing(userId, followTarget);

        return new ResponseEntity(isFollowing, HttpStatus.OK);
    }

    @Operation(summary = "팔로잉/팔로워 수 확인", description = "유저의 팔로잉/팔로워 수를 확인")
    @Parameter(name = "otherUserId", description = "확인할 유저의 id", required = true)
    @GetMapping("/countFollow")
    public ResponseEntity countFollow(@Parameter(hidden = true)String userId, String otherUserId) {

        if(otherUserId == null) otherUserId = userId;

        Long countFollowing = followService.countFollowing(otherUserId);
        Long countFollower = followService.countFollower(otherUserId);
//        Long countBlock = followService.countBlock(otherUserId);

        Map<String, Object> resultData = new HashMap<>();
        resultData.put("countFollowing", countFollowing);
        resultData.put("countFollower", countFollower);
//        resultData.put("countBlock", countBlock);

        return new ResponseEntity(resultData, HttpStatus.OK);
    }

}
