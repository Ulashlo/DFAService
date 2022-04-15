package com.hse.dfa.backend.service.dfa;

import com.hse.dfa.backend.contracts.DFA;
import com.hse.dfa.backend.contracts.Exchanger;
import com.hse.dfa.backend.controller.dto.dfa.DFAInfoForCreateDTO;
import com.hse.dfa.backend.exceptions.contract.UserEthereumAddressAbsentException;
import com.hse.dfa.backend.service.user_info.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class DFAServiceImpl implements DFAService {
    private final Web3j web3j;
    private final Exchanger exchanger;
    private final ContractGasProvider gasProvider;
    private final UserService userService;

    @Override
    public String createDFA(DFAInfoForCreateDTO dto) throws Exception {
        final var currentUser = userService.getCurrentUser();
        final var dfa = DFA.deploy(
            web3j,
            new ClientTransactionManager(
                web3j,
                currentUser.getAddress().orElseThrow(
                    () -> new UserEthereumAddressAbsentException(
                        format(
                            "User with username = %s dont have ethereum address!",
                            currentUser.getUsername()
                        )
                    )
                )
            ),
            gasProvider,
            BigInteger.valueOf(dto.getInitialSupply()),
            exchanger.getContractAddress(),
            dto.getName(),
            dto.getSymbol()
        ).send();
        return dfa.getContractAddress();
    }

    @Override
    public Long getBalance(String dfaAddress) throws Exception {
        final var user = userService.getCurrentUser();
        final var userAddress = user.getAddress().orElseThrow(
            () -> new UserEthereumAddressAbsentException(
                format(
                    "User with username = %s dont have ethereum address!",
                    user.getUsername()
                )
            )
        );
        final var dfa = DFA.load(
            dfaAddress,
            web3j,
            new ReadonlyTransactionManager(
                web3j,
                userAddress
            ),
            gasProvider
        );
        return dfa.balanceOf(userAddress).send()
            .longValue();
    }
}
