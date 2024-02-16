package com.movie.test.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", securityScheme())
                )
                .security(Arrays.asList(securityRequirement()))
                .info(apiInfo());
    }

    private Info apiInfo(){
        return new Info()
                .title("감상회 api 명세서")
                .description("감상회 Back <-> Front api 사용을 위한 레퍼런스")
                .version("1.0.0");
    }

    private SecurityScheme securityScheme(){
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");
    }

    private SecurityRequirement securityRequirement(){
        return new SecurityRequirement().addList("bearerAuth");
    }
}
