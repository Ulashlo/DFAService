package com.hse.dfa.backend.exceptions.authentication;

public class TokenParsingException extends CustomAuthenticationException {
    public TokenParsingException(String message) {
        super(message);
    }

    public TokenParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getUserMessage() {
        return "Ошибка парсинга токена!";
    }
}
