package com.movie.test.common.config;

import com.movie.test.common.cef.SimplePasswordEncoder;
import com.movie.test.common.filter.JwtAuthenticationFilter;
import com.movie.test.user.token.service.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    private String[] excludePath = {"/login", "/user/signup", "/error", "/h2-console/**"};
    private String[] swagger = {"/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/api-docs/**"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOption -> frameOption.sameOrigin())) // h2 사용을 위한 설정
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(formLogin -> formLogin.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                Stream.of(excludePath).map(AntPathRequestMatcher::antMatcher).toArray(AntPathRequestMatcher[]::new)
                        ).permitAll()
                        .requestMatchers(
                                Stream.of(swagger).map(AntPathRequestMatcher::antMatcher).toArray(AntPathRequestMatcher[]::new)
                        ).permitAll()
//                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .anyRequest().authenticated() // 그 외 요청은 인증 필요
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        // TODO 나중에 모듈 변경하기 -> BCryptPasswordEncoder()
        return new SimplePasswordEncoder();
    }
}
