package com.hse.dfa.backend.configuration.contracts;

import com.hse.dfa.backend.contracts.DFA;
import com.hse.dfa.backend.properties.contracts.DFAProperties;
import com.hse.dfa.backend.properties.contracts.OwnerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;

@Configuration
@RequiredArgsConstructor
public class DFAConfig {
    private final Web3j web3j;
    private final ContractGasProvider gasProvider;
    private final DFAProperties dfa;
    private final OwnerProperties owner;

    @Bean
    public DFA dfa() {
        return DFA.load(
            dfa.getAddress(),
            web3j,
            Credentials.create(owner.getKey()),
            gasProvider
        );
    }
}
