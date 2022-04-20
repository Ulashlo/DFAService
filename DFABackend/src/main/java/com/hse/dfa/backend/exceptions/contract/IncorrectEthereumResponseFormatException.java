package com.hse.dfa.backend.exceptions.contract;

public class IncorrectEthereumResponseFormatException extends CustomContractException {
    public IncorrectEthereumResponseFormatException(String message) {
        super(message);
    }

    public IncorrectEthereumResponseFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getUserMessage() {
        return "Неверный формат ответа ethereum!";
    }
}
