package com.hse.dfa.backend.service.exchanger;

import com.hse.dfa.backend.controller.dto.exchanger.ExchangeRequestDTO;
import com.hse.dfa.backend.exceptions.contract.UserEthereumAddressAbsentException;
import com.hse.dfa.backend.service.user_info.UserService;
import com.hse.dfa.backend.util.contracts.ContractFabric;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ExchangerServiceImpl implements ExchangerService {
    private final ContractFabric contractFabric;
    private final UserService userService;

    @Override
    public void addExchangeRequest(ExchangeRequestDTO dto) throws Exception {
        final var user = userService.getCurrentUser();
        final var userAddress = user.getAddress().orElseThrow(
            () -> new UserEthereumAddressAbsentException(
                format(
                    "User with username = %s dont have ethereum address!",
                    user.getUsername()
                )
            )
        );
        final var factory = contractFabric.factory(userAddress);
        final var exchangerAddress = factory.getExchanger(dto.getDfaToGive()).send();
        final var exchanger = contractFabric.exchanger(userAddress, exchangerAddress);
        final var dfa = contractFabric.dfa(userAddress, dto.getDfaToGive());
        dfa.approve(exchangerAddress, BigInteger.valueOf(dto.getAmountToGive()));
        exchanger.addRequest(
            dto.getDfaToGet(),
            BigInteger.valueOf(dto.getAmountToGet()),
            BigInteger.valueOf(dto.getAmountToGive())
        ).send();
    }
}
