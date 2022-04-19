package com.hse.dfa.backend.configuration.contracts;

import com.hse.dfa.backend.contracts.Exchanger_old;
import com.hse.dfa.backend.properties.contracts.ExchangerProperties;
import com.hse.dfa.backend.properties.contracts.OwnerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;

@Configuration
@RequiredArgsConstructor
public class ExchangerConfig {
    private final Web3j web3j;
    private final ContractGasProvider gasProvider;
    private final ExchangerProperties exchanger;
    private final OwnerProperties owner;

    @Bean
    public Exchanger_old exchanger() {
        return Exchanger_old.load(
            exchanger.getAddress(),
            web3j,
            Credentials.create(owner.getKey()),
            gasProvider
        );
    }
}
