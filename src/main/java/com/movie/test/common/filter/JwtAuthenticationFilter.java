package com.movie.test.common.filter;

import com.movie.test.common.cef.ModifiableHttpServletRequest;
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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("요청 URI : {}", request.getRequestURI());
        log.info("시큐리티 필터 진입");
        String token = jwtTokenProvider.resolveToken(request);

        if(token != null && jwtTokenProvider.validateToken(token)){
            log.info("시큐리티 필터 토큰 검증 성공");
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // token에서 userId 추출하여 request에 parameter로 추가.
            ModifiableHttpServletRequest tempRequest = new ModifiableHttpServletRequest(request);
            tempRequest.setParameter("userId", jwtTokenProvider.getUserId(token));
            request = (HttpServletRequest) tempRequest;
        }

        filterChain.doFilter(request, response);

    }
}
