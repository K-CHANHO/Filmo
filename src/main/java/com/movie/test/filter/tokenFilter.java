package com.movie.test.filter;

import com.movie.test.token.service.tokenService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// TODO : 토크 체크 -> 모든 요청시 토큰 리프레쉬
@Slf4j
public class tokenFilter implements Filter {

    @Autowired
    private tokenService tokenService;

    private List<String> excludedUrls = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        excludedUrls.add("/login");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("--- Start Token Filter ---");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getServletPath();
        if(!excludedUrls.contains(path)){
            String token = req.getParameter("token");
            if(token == null){
                res.setStatus(403);
                return;
            }

            Claims claims = tokenService.readJwtToken(token);
            Date expiration = claims.getExpiration();

            log.info("expiration date = {}", expiration);

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
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        log.info("--- End Token Filter ---");
        Filter.super.destroy();
    }
}
