package com.movie.test.config;

import com.movie.test.filter.tokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<tokenFilter> tokenFilter(){
        FilterRegistrationBean<tokenFilter> tokenFilterBean = new FilterRegistrationBean<>();
        tokenFilterBean.setFilter(new tokenFilter());
        tokenFilterBean.addUrlPatterns("/*");
        tokenFilterBean.setOrder(1);
        tokenFilterBean.setName("tokenFilter");
        return tokenFilterBean;
    }
}
