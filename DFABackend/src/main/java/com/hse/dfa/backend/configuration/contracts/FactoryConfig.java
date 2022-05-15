package com.hse.dfa.backend.configuration.contracts;

import com.hse.dfa.backend.properties.contracts.FactoryProperties;
import com.hse.dfa.backend.properties.contracts.OwnerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;

@Configuration
@RequiredArgsConstructor
public class FactoryConfig {
    private final Web3j web3j;
    private final ContractGasProvider gasProvider;
    private final FactoryProperties factory;
    private final OwnerProperties owner;
}
