package com.hse.dfa.backend.exceptions.userInfo;

public class IssuerCanNotChangeEthereumInfoException extends CustomUserInfoException {
    public IssuerCanNotChangeEthereumInfoException(String message) {
        super(message);
    }

    public IssuerCanNotChangeEthereumInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getUserMessage() {
        return "Верифицированный пользователь не может обновлять информацию об аккаунте ethereum!";
    }
}
