package com.movie.test.report.like.controller;

import com.movie.test.report.like.dto.LikeDTO;
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
    @Parameters(value = {
            @Parameter(name = "targetId", description = "감상문 OR 댓글 id", required = true),
            @Parameter(name = "type", description = "'report' OR 'reply'", required = true)
    })
    @PostMapping("/regist")
    public ResponseEntity registLike(LikeDTO likeDTO, @AuthenticationPrincipal CustomUser user) {
        likeService.registLike(likeDTO, user.getUserId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "좋아요 취소", description = "좋아요를 취소합니다.")
    @Parameters(value = {
            @Parameter(name = "likeId", description = "like id", required = true)
    })
    @PostMapping("/cancel")
    public ResponseEntity cancelLike(LikeDTO likeDTO, @AuthenticationPrincipal CustomUser user) {
        likeService.cancelLike(likeDTO.getLikeId(), user.getUserId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "좋아요 확인", description = "감상문이나 댓글을 좋아요 했는지 확인합니다.")
    @Parameters(value = {
            @Parameter(name = "targetId", description = "감상문 OR 댓글 id", required = true),
            @Parameter(name = "type", description = "'report' OR 'reply'", required = true)
    })
    @GetMapping("/check")
    public ResponseEntity checkLikst(LikeDTO likeDTO, @AuthenticationPrincipal CustomUser user){
        boolean isExistLike = likeService.checkLike(likeDTO, user.getUserId());
        return new ResponseEntity(isExistLike, HttpStatus.OK);
    }

    @Operation(summary = "좋아요 수 확인", description = "감상문이나 댓글의 좋아요 수를 확인합니다.")
    @Parameters(value = {
            @Parameter(name = "targetId", description = "감상문 OR 댓글 id", required = true)
    })
    @GetMapping("/count")
    public ResponseEntity countLike(LikeDTO likeDTO){
        Long countLike = likeService.countLike(likeDTO.getTargetId());
        return new ResponseEntity(countLike, HttpStatus.OK);
    }
}
