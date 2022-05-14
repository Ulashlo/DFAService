package com.hse.dfa.backend.contracts;

import java.math.BigInteger;

public enum ExchangeType {
    INDIVISIBLE(0), DIVISIBLE(1);

    private final int code;

    ExchangeType(int code) {
        this.code = code;
    }

    public BigInteger getCode() {
        return BigInteger.valueOf(code);
    }
}
