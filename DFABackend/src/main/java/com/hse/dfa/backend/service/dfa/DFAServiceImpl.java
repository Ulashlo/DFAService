package com.hse.dfa.backend.service.dfa;

import com.hse.dfa.backend.controller.dto.dfa.DFAInfoForCreateDTO;
import com.hse.dfa.backend.exceptions.contract.UserEthereumAddressAbsentException;
import com.hse.dfa.backend.service.user_info.UserService;
import com.hse.dfa.backend.util.contracts.ContractFabric;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class DFAServiceImpl implements DFAService {
    private final UserService userService;
    private final ContractFabric contractFabric;

    @Override
    public String createDFA(DFAInfoForCreateDTO dto) throws Exception {
        final var currentUser = userService.getCurrentUser();
        final var userAddress = currentUser.getAddress().orElseThrow(
            () -> new UserEthereumAddressAbsentException(
                format(
                    "User with username = %s dont have ethereum address!",
                    currentUser.getUsername()
                )
            )
        );
        final var factory = contractFabric.factory(userAddress);
        return factory.createDfa(
            BigInteger.valueOf(dto.getInitialSupply()),
            dto.getName(),
            dto.getSymbol()
        ).send().getContractAddress();
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
        final var dfa = contractFabric.dfa(dfaAddress);
        return dfa.balanceOf(userAddress).send().longValue();
    }

    @Override
    public List<String> getAllDfa() throws Exception {
        final var factory = contractFabric.factory();
        return factory.getAllDfa().send();
    }
}
