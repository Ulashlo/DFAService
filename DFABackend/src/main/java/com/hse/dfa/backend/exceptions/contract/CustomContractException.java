package com.hse.dfa.backend.exceptions.contract;

import com.hse.dfa.backend.exceptions.HumanUnderstandableException;

public abstract class CustomContractException
    extends RuntimeException
    implements HumanUnderstandableException {
    protected CustomContractException(String message) {
        super(message);
    }
    protected CustomContractException(String message, Throwable cause) {
        super(message, cause);
    }
}
