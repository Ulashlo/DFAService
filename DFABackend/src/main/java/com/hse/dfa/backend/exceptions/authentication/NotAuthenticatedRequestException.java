package com.hse.dfa.backend.exceptions.authentication;

public class NotAuthenticatedRequestException extends CustomAuthenticationException{
    public NotAuthenticatedRequestException(String message) {
        super(message);
    }

    public NotAuthenticatedRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getUserMessage() {
        return "Пользователь не авторизаван!";
    }
}
