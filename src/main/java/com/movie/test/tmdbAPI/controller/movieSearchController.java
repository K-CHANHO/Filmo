package com.movie.test.tmdbAPI.controller;

import com.google.gson.JsonObject;
import com.movie.test.tmdbAPI.dto.movieSearchApiDTO;
import com.movie.test.tmdbAPI.service.movieSearchService;
import com.movie.test.user.dto.userDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Map;

@Tag(name = "영화정보", description = "영화 정보 API")
@Controller
@Slf4j
public class movieSearchController {

    @Autowired
    private movieSearchService movieSearchService;

    @Operation(summary = "영화 정보 검색", description = "영화 정보를 검색합니다.")
    @Parameters(value = {
            @Parameter(name = "query", description = "영화 제목", required = true),
            @Parameter(name = "includeAdult", description = "성인물 포함여부", required = true),
            @Parameter(name = "language", description = "언어", required = true),
            @Parameter(name = "primaryReleaseYear", description = "최초 개봉연도", required = true),
            @Parameter(name = "page", description = "페이지", required = true),
            @Parameter(name = "region", description = "국가", required = true),
            @Parameter(name = "year", description = "개봉연도", required = true)
    })
    @ApiResponse(responseCode = "200", description = "회원가입 완료 시 가입된 회원정보 리턴")
    @GetMapping("/search")
    public ResponseEntity movieSearch(movieSearchApiDTO searchDTO, HttpServletRequest request, HttpServletResponse response){

        log.info("Start Movie Search API");
        Map<String, String> movieInfo = (Map<String, String>) movieSearchService.getMovieInfo(searchDTO);
        log.info("End Movie Search API");
        return new ResponseEntity(movieInfo, HttpStatus.OK);
    }
}
