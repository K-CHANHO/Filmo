package com.movie.test.Interceptor;

import com.movie.test.token.service.tokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

// TODO : 토크 체크 -> 모든 요청시 토큰 리프레쉬
@Slf4j
@Component
public class tokenInterceptor implements HandlerInterceptor {

    @Autowired
    private tokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("--- Start Token Interceptor ---");

        String token = request.getParameter("token");
        if(token == null){
            response.setStatus(500);
            response.setHeader("token", "testtest");
            return false;
        }

        try{
            Claims claims = tokenService.readJwtToken(token);
            Date expiration = claims.getExpiration();

            log.info("expiration date = {}", expiration);

            String uid = (String) claims.get("uid");
            String type = (String) claims.get("type");
            String newToken = tokenService.makeJwtToken(uid, type);
            request.setAttribute("originToken", token);
            request.setAttribute("newToken", newToken);
//            response.setHeader("originToken", token);
//            response.setHeader("newToken", newToken);
            response.setStatus(200);
            log.info("--- End Token Interceptor ---");
            return true;
        } catch (ExpiredJwtException e){
            response.setStatus(403);
            log.info("--- End Token Interceptor ---");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
