package com.hse.dfa.backend.exceptions.contract;

public class UserEthereumCredentialException extends CustomContractException {
    public UserEthereumCredentialException(String message) {
        super(message);
    }

    public UserEthereumCredentialException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getUserMessage() {
        return "Пользователь не указал свой ethereum адресс или приватный ключ!";
    }
}
