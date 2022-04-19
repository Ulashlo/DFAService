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

    public Factory factory() {
        return Factory.load(
            factory.getAddress(),
            web3j,
            Credentials.create(owner.getAddress()),
            gasProvider
        );
    }

    public Factory factory(String userAddress) {
        return Factory.load(
            factory.getAddress(),
            web3j,
            Credentials.create(userAddress),
            gasProvider
        );
    }

    public Exchanger exchanger(String exchangerAddress) {
        return Exchanger.load(
            exchangerAddress,
            web3j,
            Credentials.create(owner.getAddress()),
            gasProvider
        );
    }

    public Exchanger exchanger(String userAddress, String exchangerAddress) {
        return Exchanger.load(
            exchangerAddress,
            web3j,
            Credentials.create(userAddress),
            gasProvider
        );
    }

    public DFA dfa(String dfaAddress) {
        return DFA.load(
            dfaAddress,
            web3j,
            Credentials.create(owner.getAddress()),
            gasProvider
        );
    }

    public DFA dfa(String userAddress, String dfaAddress) {
        return DFA.load(
            dfaAddress,
            web3j,
            Credentials.create(userAddress),
            gasProvider
        );
    }
}
