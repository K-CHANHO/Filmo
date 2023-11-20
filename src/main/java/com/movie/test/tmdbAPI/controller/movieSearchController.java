package com.movie.test.tmdbAPI.controller;

import com.google.gson.JsonObject;
import com.movie.test.tmdbAPI.dto.movieSearchApiDTO;
import com.movie.test.tmdbAPI.service.movieSearchService;
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

@Controller
@Slf4j
public class movieSearchController {

    @Autowired
    private movieSearchService movieSearchService;

    @GetMapping("/search")
    public ResponseEntity movieSearch(movieSearchApiDTO searchDTO, HttpServletRequest request, HttpServletResponse response){

        log.info("Start Movie Search API");
        Map<String, String> movieInfo = (Map<String, String>) movieSearchService.getMovieInfo(searchDTO);
        log.info("End Movie Search API");
        return new ResponseEntity(movieInfo, HttpStatus.OK);
    }
}
