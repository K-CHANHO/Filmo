package com.movie.test.filter;

import com.movie.test.token.service.tokenService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;

// TODO : 토크 체크 -> 모든 요청시 토큰 리프레쉬
@Slf4j
public class tokenFilter implements Filter {

    @Autowired
    private tokenService tokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("--- Start Token Filter ---");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String token = req.getHeader("token");

        Claims claims = tokenService.readJwtToken(token);
        Date expiration = claims.getExpiration();

        // 토큰 만료일이 지난 경우 재로그인
        if (expiration.before(new Date(new Date().getTime()))) {
            res.setStatus(403);

        } else {
            String uid = (String) claims.get("uid");
            String type = (String) claims.get("type");
            String newToken = tokenService.makeJwtToken(uid, type);
            res.setHeader("token", newToken);
        }


    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        log.info("--- End Token Filter ---");
        Filter.super.destroy();
    }
}
