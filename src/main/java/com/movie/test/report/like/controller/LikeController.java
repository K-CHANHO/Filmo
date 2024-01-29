package com.movie.test.report.like.controller;

import com.movie.test.report.like.dto.LikeDTO;
import com.movie.test.report.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/regist")
    public ResponseEntity registOrDeleteLike(LikeDTO likeDTO) {

        likeService.registOrDelete(likeDTO);

        return new ResponseEntity(HttpStatus.OK);
    }
}
