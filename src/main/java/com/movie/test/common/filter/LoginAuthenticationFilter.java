package com.movie.test.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;

public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public LoginAuthenticationFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        String method = request.getMethod();

        if (!method.equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported : " + method);
        }

        ServletInputStream inputStream = request.getInputStream();
        LoginRequestDTO loginRequestDTO = new ObjectMapper().readValue(inputStream, LoginRequestDTO.class);

        return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.uid, loginRequestDTO.type));
    }

    private record LoginRequestDTO(
            String uid,
            String type
    ){}
}
