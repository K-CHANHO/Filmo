package com.movie.test.user.block.controller;

import com.movie.test.user.block.dto.BlockDeleteDto;
import com.movie.test.user.block.dto.BlockDto;
import com.movie.test.user.block.dto.BlockSaveDto;
import com.movie.test.user.block.service.BlockService;
import com.movie.test.user.follow.dto.FollowListSearchDTO;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/block")
@Tag(name = "차단", description = "차단 관련 API")
@RequiredArgsConstructor
@Slf4j
public class BlockController {

    private final BlockService blockService;
    private final UserService userService;

    @Operation(summary = "차단 등록", description = "차단을 등록합니다.")
    @ApiResponse(responseCode = "200", description = "차단 정보 리턴")
    @PostMapping("/save")
    public ResponseEntity saveBlock(@RequestBody BlockSaveDto blockSaveDto, @AuthenticationPrincipal CustomUser user) {

        blockSaveDto.setUserId(user.getUserId());
        BlockDto blockDto = blockService.saveBlock(blockSaveDto);

        return new ResponseEntity(blockDto, HttpStatus.OK);
    }

    @Operation(summary = "차단 취소", description = "차단을 취소합니다.")
    @ApiResponse(responseCode = "200")
    @DeleteMapping("/delete")
    public ResponseEntity deleteBlock(@RequestBody BlockDeleteDto blockDeleteDto, @AuthenticationPrincipal CustomUser user){

        blockDeleteDto.setUserId(user.getUserId());
        String deletedId = blockService.deleteBlock(blockDeleteDto);

        return new ResponseEntity(deletedId, HttpStatus.OK);
    }

    @Operation(summary = "차단 목록", description = "차단 목록을 조회합니다.")
    @Parameters({
        @Parameter(name = "lastUserId", description = "마지막으로 조회된 유저 id"),
        @Parameter(name = "keyword", description = "검색어"),
    })
    @ApiResponse(responseCode = "200", description = "팔로잉/차단 목록 리턴")
    @GetMapping("/list")
    public ResponseEntity getBlockList(@RequestBody FollowListSearchDTO blockListSearchDTO, @Parameter(hidden = true) Pageable pageable){

        // Slice로 구현 : List를 먼저 구하고 그 안에서 Slice로 자르기.
        Slice<UserDto> blockUserInfo = blockService.getBlockList(blockListSearchDTO, pageable);

        Map<String, Object> resultData = new HashMap<>();
        resultData.put("blockList", blockUserInfo.getContent());
        resultData.put("hasNext", blockUserInfo.hasNext());

        return new ResponseEntity(resultData, HttpStatus.OK);
    }

    @Operation(summary = "차단 확인", description = "상대를 차단하고 있는 지 확인")
    @Parameters({
            @Parameter(name = "targetId", description = "상대 id", required = true)
    })
    @ApiResponse(responseCode = "200", description = "차단하고 있는 경우 true, 아닌 경우 false 리턴")
    @GetMapping("/isBlock")
    public ResponseEntity isFollowing(@Parameter(hidden = true)String userId, String targetId){

        boolean isBlocking = blockService.isBlocking(userId, targetId);

        return new ResponseEntity(isBlocking, HttpStatus.OK);
    }

    @Operation(summary = "차단 수 확인", description = "내가 차단하고 있는 유저의 수를 확인")
    @Parameter(hidden = true)
    @GetMapping("/count")
    public ResponseEntity countFollow(@Parameter(hidden = true)String userId) {

        Long blockCount = blockService.countBlock(userId);

        Map<String, Object> resultData = new HashMap<>();
        resultData.put("blockCount", blockCount);

        return new ResponseEntity(resultData, HttpStatus.OK);
    }

}
