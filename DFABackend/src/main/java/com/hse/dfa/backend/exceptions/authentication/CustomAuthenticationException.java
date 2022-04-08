package com.hse.dfa.backend.exceptions.authentication;

import com.hse.dfa.backend.exceptions.HumanUnderstandableException;
import org.springframework.security.core.AuthenticationException;

public abstract class CustomAuthenticationException
    extends AuthenticationException
    implements HumanUnderstandableException {
    protected CustomAuthenticationException(String message) {
        super(message);
    }
    protected CustomAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
