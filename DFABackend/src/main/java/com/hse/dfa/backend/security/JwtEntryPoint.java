package com.hse.dfa.backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hse.dfa.backend.exceptions.authentication.CustomAuthenticationException;
import com.hse.dfa.backend.exceptions.authentication.TokenExpiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.hse.dfa.backend.controller.error.ApiError.createError;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
@RequiredArgsConstructor
public class JwtEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException
    ) throws IOException, ServletException {
        final var errorInfo = createError(
            authException instanceof TokenExpiredException
                ? NOT_ACCEPTABLE
                : UNAUTHORIZED,
            determineMessage(authException));
        response.setContentType("application/json");
        response.setStatus(errorInfo.getStatus());
        final var out = response.getOutputStream();
        objectMapper.writeValue(out, errorInfo);
    }

    private String determineMessage(AuthenticationException authenticationException) {
        if (authenticationException instanceof CustomAuthenticationException) {
            return ((CustomAuthenticationException) authenticationException).getUserMessage();
        }
        return "Ошибка авторизации";
    }
}
