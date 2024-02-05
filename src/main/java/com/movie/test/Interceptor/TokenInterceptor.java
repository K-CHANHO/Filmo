package com.movie.test.Interceptor;

import com.movie.test.user.token.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// TODO : 토크 체크 -> 모든 요청시 토큰 리프레쉬
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("--- Start Token Interceptor ---");

        String token = request.getHeader("Authorization");

        if(token == null){
            log.error("--- Toke is Null! ---");
            response.sendError(403, "Token is NULL! Please Login Again");
            return false;
        }

        token = tokenService.resolveToken(token);

        try{
            Claims claims = tokenService.readJwtToken(token);

            String newToken = tokenService.makeJwtToken((String) claims.get("userId"));
            request.setAttribute("userId", (String) claims.get("userId"));
            response.setHeader("Authorization", "Bearer " + newToken);

            log.info("--- End Token Interceptor ---");
            return true;
        } catch (ExpiredJwtException e){
            log.error("--- Expired Token! : {} ---", e);
            response.sendError(403, "Token is EXPIRED! Please Login Again");
            return false;
        } catch (Exception e) {
            log.error("--- Invalid Token! : {} ---", e);
            response.sendError(403, "There is a problem. Ask to admin");
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
