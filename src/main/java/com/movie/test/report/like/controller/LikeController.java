package com.movie.test.report.like.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.movie.test.report.like.dto.LikeCheckDto;
import com.movie.test.report.like.dto.LikeDto;
import com.movie.test.report.like.dto.LikeSaveDto;
import com.movie.test.report.like.service.LikeService;
import com.movie.test.user.userinfo.dto.CustomUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "좋아요", description = "좋아요 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    @Operation(summary = "좋아요 등록", description = "좋아요를 등록합니다.")
    @PostMapping("/save")
    public ResponseEntity saveLike(@RequestBody LikeSaveDto likeSaveDto, @AuthenticationPrincipal CustomUser user) {
        LikeDto savedLike = likeService.saveLike(likeSaveDto, user.getUserId());
        return new ResponseEntity(savedLike, HttpStatus.OK);
    }

    @Operation(summary = "좋아요 취소", description = "좋아요를 취소합니다.")
    @Parameters(value = {
            @Parameter(name = "likeId", description = "like id", required = true)
    })
    @DeleteMapping("/cancel")
    public ResponseEntity cancelLike(String likeId, @AuthenticationPrincipal CustomUser user) {
        likeService.cancelLike(likeId, user.getUserId());

        JsonObject returnData = new JsonObject();
        returnData.addProperty("likeId", likeId);

        return new ResponseEntity(returnData, HttpStatus.OK);
    }

    @Operation(summary = "좋아요 확인", description = "감상문이나 댓글을 좋아요 했는지 확인합니다.")
    @Parameters(value = {
            @Parameter(name = "targetId", description = "감상문 OR 댓글 id", required = true),
            @Parameter(name = "type", description = "'report' OR 'reply'", required = true)
    })
    @GetMapping("/check")
    public ResponseEntity checkLike(LikeDto likeDTO, @AuthenticationPrincipal CustomUser user){

        LikeDto likeDto = likeService.getLikeDto(likeDTO);
        boolean isExistLike = likeService.checkLike(likeDTO, user.getUserId());

        LikeCheckDto returnData = LikeCheckDto.builder()
                .likeId(likeDto.getLikeId())
                .isLike(isExistLike)
                .build();

        return new ResponseEntity(returnData, HttpStatus.OK);
    }

    @Operation(summary = "좋아요 수 확인", description = "감상문이나 댓글의 좋아요 수를 확인합니다.")
    @Parameters(value = {
            @Parameter(name = "targetId", description = "감상문 OR 댓글 id", required = true)
    })
    @GetMapping("/count")
    public ResponseEntity countLike(String targetId){
        Long countLike = likeService.countLike(targetId);

        JsonObject returnData = new JsonObject();
        returnData.addProperty("countLike", countLike);

        return new ResponseEntity(returnData, HttpStatus.OK);
    }
}
