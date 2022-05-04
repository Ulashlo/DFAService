package com.hse.dfa.backend.exceptions.request;

public class UserIsAlreadyIssuerException extends CustomRequestException {
    public UserIsAlreadyIssuerException(String message) {
        super(message);
    }

    public UserIsAlreadyIssuerException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getUserMessage() {
        return "Вы уже имеете право выпуска ЦФА!";
    }
}
