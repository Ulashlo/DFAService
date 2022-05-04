package com.hse.dfa.backend.exceptions.userInfo;

import com.hse.dfa.backend.exceptions.HumanUnderstandableException;

public abstract class CustomUserInfoException
    extends RuntimeException
    implements HumanUnderstandableException {
    protected CustomUserInfoException(String message) {
        super(message);
    }

    protected CustomUserInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}
