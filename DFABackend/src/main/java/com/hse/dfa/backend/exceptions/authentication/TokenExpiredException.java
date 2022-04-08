package com.hse.dfa.backend.exceptions.authentication;

public class TokenExpiredException extends CustomAuthenticationException {
    public TokenExpiredException(String message) {
        super(message);
    }

    public TokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getUserMessage() {
        return "Токен устарел!";
    }
}
