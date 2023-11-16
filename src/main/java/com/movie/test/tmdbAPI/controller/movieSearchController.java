package com.movie.test.tmdbAPI.controller;

import com.google.gson.JsonObject;
import com.movie.test.tmdbAPI.dto.movieSearchApiDTO;
import com.movie.test.tmdbAPI.service.movieSearchService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class movieSearchController {

    @Autowired
    private movieSearchService movieSearchService;

    @GetMapping("/search")
    public ResponseEntity movieSearch(movieSearchApiDTO searchDTO, HttpServletRequest request, HttpServletResponse response){

        JsonObject movieInfo = movieSearchService.getMovieInfo(searchDTO);

        return new ResponseEntity(movieInfo, HttpStatus.OK);
    }
}
