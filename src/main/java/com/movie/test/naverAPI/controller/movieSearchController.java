package com.movie.test.naverAPI.controller;

import com.movie.test.naverAPI.dto.movieSearchApiDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class movieSearchController {

    @GetMapping("/search")
    public ResponseEntity movieSearch(movieSearchApiDTO searchDTO){


        return new ResponseEntity(null);
    }
}
