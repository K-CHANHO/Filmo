package com.movie.test.common.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfig {

    @Bean
    Gson gson(){
        return new GsonBuilder().setPrettyPrinting().serializeNulls().create();
//        return new Gson();
    }
}
