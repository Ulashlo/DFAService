package com.hse.dfa.backend.exceptions.request;

import com.hse.dfa.backend.exceptions.HumanUnderstandableException;

public abstract class CustomRequestException
    extends RuntimeException
    implements HumanUnderstandableException {
    protected CustomRequestException(String message) {
        super(message);
    }
    protected CustomRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
