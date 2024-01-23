package com.movie.test.api.tmdbAPI.controller;

import com.movie.test.api.tmdbAPI.dto.*;
import com.movie.test.api.tmdbAPI.service.TmdbService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "영화정보", description = "영화 정보 API")
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieSearchController {

    private final TmdbService tmdbService;

    @Operation(summary = "영화 검색 리스트", description = "영화 검색 리스트를 조회합니다.")
    @Parameters(value = {
            @Parameter(name = "query", description = "검색어", required = true),
            @Parameter(name = "page", description = "페이지, 기본값 1"),
    })
    @ApiResponse(responseCode = "200", description = "영화 리스트 리턴")
    @GetMapping("/searchList")
    public ResponseEntity movieSearchList(MovieSearchApiDTO movieSearchApiDTO){

        MovieSearchResponseDTO movieInfo = (MovieSearchResponseDTO) tmdbService.getMovieSearchList(movieSearchApiDTO);

        return new ResponseEntity(movieInfo, HttpStatus.OK);
    }

    @Operation(summary = "영화 상세 정보 검색", description = "영화 상제 정보를 검색합니다.")
    @Parameters(value = {
            @Parameter(name = "movieId", description = "영화 id", required = true),
    })
    @ApiResponse(responseCode = "200", description = "영화 상세정보 리턴")
    @GetMapping("/searchDetail")
    public ResponseEntity movieDetailInfo(MovieSearchApiDTO movieSearchApiDTO){

        MovieDetailInfoDTO movieInfo = (MovieDetailInfoDTO) tmdbService.getMovieDetailInfo(movieSearchApiDTO);
        movieInfo.setProviders(tmdbService.getProviders(movieSearchApiDTO.getMovieId()));

        return new ResponseEntity(movieInfo, HttpStatus.OK);
    }

    @Operation(summary = "영화 이미지 조회", description = "영화 이미지를 조회합니다.")
    @Parameters(value = {
            @Parameter(name = "movieId", description = "영화 id", required = true),
    })
    @ApiResponse(responseCode = "200", description = "영화 이미지 정보 리턴, 사용법 : {base_url} + {size} + {file_path}")
    @GetMapping("/getPoster")
    public ResponseEntity getMoviePoster(MovieSearchApiDTO movieSearchApiDTO){

        Map<String, Object> returnData = new HashMap<>();

        MovieConfigurationDTO configuration = (MovieConfigurationDTO) tmdbService.getConfiguration();
        MovieImageDTO images = (MovieImageDTO) tmdbService.getImages(movieSearchApiDTO); // base_url + size + file_path

        returnData.put("configuration", configuration);
        returnData.put("images", images);

        return new ResponseEntity(returnData, HttpStatus.OK);
    }

    @Operation(summary = "영화 관련 동영상 조회", description = "영화 동영상을 조회합니다.")
    @Parameters(value = {
            @Parameter(name = "movieId", description = "영화 id", required = true),
    })
    @ApiResponse(responseCode = "200", description = "영화 동영상 정보 리턴, 사용법 => 'site'가 'YouTube'일 경우 : https://www.youtube.com/embed/ + {key}")
    @GetMapping("/getVideo")
    public ResponseEntity getMovieVideo(MovieSearchApiDTO movieSearchApiDTO){

        MovieVideoDTO videos = (MovieVideoDTO) tmdbService.getVideos(movieSearchApiDTO);

        return new ResponseEntity(videos, HttpStatus.OK);
    }

}
