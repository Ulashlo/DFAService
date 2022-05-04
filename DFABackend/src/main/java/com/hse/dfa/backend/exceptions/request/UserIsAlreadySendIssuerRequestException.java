package com.hse.dfa.backend.exceptions.request;

public class UserIsAlreadySendIssuerRequestException extends CustomRequestException {
    public UserIsAlreadySendIssuerRequestException(String message) {
        super(message);
    }

    public UserIsAlreadySendIssuerRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getUserMessage() {
        return "Вы уже отправили запрос!";
    }
}
