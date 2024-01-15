package com.movie.test.common.config;

import com.movie.test.Interceptor.ControllerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private String[] excludePath = {"/login", "/signup"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        registry.addInterceptor(new ControllerInterceptor())
                .addPathPatterns("/**") // 인터셉터는 **을 사용해야함.
                .order(2)
        ;

        // 토큰 인터셉터 -> 개발 편의를 위해 주석.
//        registry.addInterceptor(new TokenInterceptor())
//                .addPathPatterns("/*")
//                .excludePathPatterns(excludePath)
//                .order(1)
//        ;


    }
}
