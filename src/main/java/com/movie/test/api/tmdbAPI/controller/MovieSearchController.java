package com.movie.test.api.tmdbAPI.controller;

import com.movie.test.api.tmdbAPI.dto.MovieDetailInfoDTO;
import com.movie.test.api.tmdbAPI.dto.MovieInfoDTO;
import com.movie.test.api.tmdbAPI.service.TmdbService;
import com.movie.test.api.tmdbAPI.dto.MovieSearchApiDTO;
import com.movie.test.api.tmdbAPI.dto.MovieSearchResponseDTO;
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

import java.util.List;

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
    public ResponseEntity movieSearchList(MovieSearchApiDTO searchDTO){

        MovieSearchResponseDTO movieInfo = (MovieSearchResponseDTO) tmdbService.getMovieSearchList(searchDTO);

        return new ResponseEntity(movieInfo, HttpStatus.OK);
    }

    @Operation(summary = "영화 상세 정보 검색", description = "영화 상제 정보를 검색합니다.")
    @Parameters(value = {
            @Parameter(name = "movieId", description = "영화 id", required = true),
    })
    @ApiResponse(responseCode = "200", description = "영화 상세정보 리턴")
    @GetMapping("/searchDetail")
    public ResponseEntity movieDetailInfo(MovieSearchApiDTO searchDTO){

        MovieDetailInfoDTO movieInfo = (MovieDetailInfoDTO) tmdbService.getMovieDetailInfo(searchDTO);
        movieInfo.setProviders(tmdbService.getProviders(searchDTO.getMovieId()));

        return new ResponseEntity(movieInfo, HttpStatus.OK);
    }


}
