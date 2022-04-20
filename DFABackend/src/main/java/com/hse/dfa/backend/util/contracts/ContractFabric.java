package com.hse.dfa.backend.util.contracts;

import com.hse.dfa.backend.contracts.DFA;
import com.hse.dfa.backend.contracts.Exchanger;
import com.hse.dfa.backend.contracts.Factory;
import com.hse.dfa.backend.properties.contracts.FactoryProperties;
import com.hse.dfa.backend.properties.contracts.OwnerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;

@Component
@RequiredArgsConstructor
public class ContractFabric {
    private final Web3j web3j;
    private final ContractGasProvider gasProvider;
    private final FactoryProperties factory;
    private final OwnerProperties owner;

    public Factory loadFactory() {
        return Factory.load(
            factory.getAddress(),
            web3j,
            Credentials.create(owner.getKey()),
            gasProvider
        );
    }

    public Factory loadFactory(String privateKey) {
        return Factory.load(
            factory.getAddress(),
            web3j,
            Credentials.create(privateKey),
            gasProvider
        );
    }

    public Exchanger loadExchanger(String exchangerAddress) {
        return Exchanger.load(
            exchangerAddress,
            web3j,
            Credentials.create(owner.getKey()),
            gasProvider
        );
    }

    public Exchanger loadExchanger(String privateKey, String exchangerAddress) {
        return Exchanger.load(
            exchangerAddress,
            web3j,
            Credentials.create(privateKey),
            gasProvider
        );
    }

    public DFA loadDfa(String dfaAddress) {
        return DFA.load(
            dfaAddress,
            web3j,
            Credentials.create(owner.getKey()),
            gasProvider
        );
    }

    public DFA loadDfa(String privateKey, String dfaAddress) {
        return DFA.load(
            dfaAddress,
            web3j,
            Credentials.create(privateKey),
            gasProvider
        );
    }
}
