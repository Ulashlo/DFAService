package com.hse.dfa.backend.exceptions.contract;

public class UserEthereumAddressAbsentException extends CustomContractException{
    public UserEthereumAddressAbsentException(String message) {
        super(message);
    }

    public UserEthereumAddressAbsentException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getUserMessage() {
        return "Пользователь не указал свой ethereum адресс!";
    }
}
