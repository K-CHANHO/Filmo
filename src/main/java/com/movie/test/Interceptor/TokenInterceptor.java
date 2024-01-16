package com.movie.test.Interceptor;

import com.movie.test.token.dto.TokenDTO;
import com.movie.test.token.service.TokenService;
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
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("--- Start Token Interceptor ---");

        String token = request.getHeader("token");
        if(token == null){
            log.error("--- Toke is Null! ---");
            response.sendError(403, "Token is NULL! Please Login Again");
            return false;
        }

        try{
            Claims claims = tokenService.readJwtToken(token);

            TokenDTO tokenData = TokenDTO.builder()
                    .uid((String) claims.get("uid"))
                    .type((String) claims.get("type"))
                    .build();

            String newToken = tokenService.makeJwtToken(tokenData);
            response.setHeader("token", newToken);
            log.info("--- End Token Interceptor ---");
            return true;
        } catch (ExpiredJwtException e){
            response.sendError(403, "Token is EXPIRED! Please Login Again");
            log.error("--- Expired Token! : {} ---", e);
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
