package com.hse.dfa.backend.exceptions.authentication;

import com.hse.dfa.backend.exceptions.HumanUnderstandableException;

public abstract class AuthenticationException
    extends RuntimeException
    implements HumanUnderstandableException {
    protected AuthenticationException(String message) {
        super(message);
    }
    protected AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
