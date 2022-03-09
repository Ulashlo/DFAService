package com.hse.dfa.backend.properties.web3j;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Data
@Component
@ConfigurationProperties(prefix = "web3j.gas")
public class GasProviderProperties {
    private String price;
    private String limit;

    public BigInteger getPrice() {
        return BigInteger.valueOf(Long.parseLong(price));
    }

    public BigInteger getLimit() {
        return BigInteger.valueOf(Long.parseLong(limit));
    }
}
