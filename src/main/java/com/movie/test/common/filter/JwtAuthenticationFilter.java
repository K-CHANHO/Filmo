package com.movie.test.common.filter;

import com.movie.test.user.token.service.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {

        log.info("요청 URI : {}", request.getRequestURI());
        log.info("시큐리티 필터 진입");
        String token = jwtTokenProvider.resolveToken(request);

        if(token != null && jwtTokenProvider.validateToken(token)){
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        log.info("시큐리티 필터 토큰 검증 성공");
        try {
            filterChain.doFilter(request, response);
        } catch (IOException e) {
            log.error("시큐리티 에러 : {}", e);
            throw new RuntimeException(e);
        } catch (ServletException e) {
            log.error("시큐리티 에러 : {}", e);
            throw new RuntimeException(e);
        }
    }
}
