package com.hse.dfa.backend.exceptions.authentication;

public class UsernameIsAlreadyExistException extends CustomAuthenticationException {
    public UsernameIsAlreadyExistException(String message) {
        super(message);
    }

    public UsernameIsAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getUserMessage() {
        return "Аккаунт с таким именем уже существует!";
    }
}
