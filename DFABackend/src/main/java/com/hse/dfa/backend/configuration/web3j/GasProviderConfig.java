package com.hse.dfa.backend.configuration.web3j;

import com.hse.dfa.backend.properties.web3j.GasProviderProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

@Configuration
@RequiredArgsConstructor
public class GasProviderConfig {
    private final GasProviderProperties properties;

    @Bean
    public ContractGasProvider gasProvider() {
        return new StaticGasProvider(
            properties.getPrice(),
            properties.getLimit()
        );
    }
}
