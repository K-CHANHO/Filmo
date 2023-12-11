package com.movie.test.config;

import com.movie.test.Interceptor.LogInterceptor;
import com.movie.test.Interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor())
                .order(1)
                .addPathPatterns("/*")
//                .excludePathPatterns("/getPayloadTest", "/login", "/signup", "/test/*") // 실제 운영
                .excludePathPatterns("/*") // 테스트
        ;

        registry.addInterceptor(new LogInterceptor())
                .order(2)
                .addPathPatterns("/*")
        ;
    }
}
