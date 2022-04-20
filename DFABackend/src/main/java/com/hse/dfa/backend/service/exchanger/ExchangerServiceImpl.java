package com.hse.dfa.backend.service.exchanger;

import com.hse.dfa.backend.controller.dto.exchanger.ExchangeRequestDTO;
import com.hse.dfa.backend.service.user_info.UserService;
import com.hse.dfa.backend.util.contracts.ContractFabric;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

import static com.hse.dfa.backend.util.checkers.UserChecker.checkPrivateKey;

@Service
@RequiredArgsConstructor
public class ExchangerServiceImpl implements ExchangerService {
    private final ContractFabric contractFabric;
    private final UserService userService;

    @Override
    public void addExchangeRequest(ExchangeRequestDTO dto) throws Exception {
        final var user = userService.getCurrentUser();
        final var privateKey = checkPrivateKey(user);
        final var factory = contractFabric.loadFactory(privateKey);
        final var exchangerAddress = factory.getExchanger(dto.getDfaToGive()).send();
        final var exchanger = contractFabric.loadExchanger(privateKey, exchangerAddress);
        final var dfa = contractFabric.loadDfa(privateKey, dto.getDfaToGive());
        dfa.approve(exchangerAddress, BigInteger.valueOf(dto.getAmountToGive())).send();
        exchanger.addRequest(
            dto.getDfaToGet(),
            BigInteger.valueOf(dto.getAmountToGet()),
            BigInteger.valueOf(dto.getAmountToGive())
        ).send();
    }
}
