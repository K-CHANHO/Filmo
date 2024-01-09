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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "팔로우", description = "팔로우 관련 API")
@RequiredArgsConstructor
@Slf4j
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

    @Operation(summary = "팔로잉 목록", description = "팔로잉을 목록을 조회합니다.")
    @Parameter(name = "userId", description = "유저 id", required = true)
    @ApiResponse(responseCode = "200", description = "팔로잉 목록 리턴")
    @GetMapping("/follow/followingList")
    public ResponseEntity getFollowingList(String userId, @PageableDefault(size = 20, sort = "createDate", direction = Sort.Direction.DESC)Pageable pageable){

        // TODO : JPA, Slice, Pageable 공부
        // Slice<FollowDTO> followingList = followService.getFollowingList(userId, pageable);

        Slice<FollowDTO> followingList = followService.getFollowingList(userId);

        Map<String, Object> resultData = new HashMap<>();
        resultData.put("list", followingList.getContent());

        return new ResponseEntity(followingList.getContent(), HttpStatus.OK);
    }
}
