package com.hse.dfa.backend.exceptions.userInfo;

public class WrongCredentialsException extends CustomUserInfoException {
    public WrongCredentialsException(String message) {
        super(message);
    }

    public WrongCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getUserMessage() {
        return "Некорректные адрес или приватный ключ!";
    }
}
