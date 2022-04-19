package com.hse.dfa.backend.service.exchanger;

import com.hse.dfa.backend.contracts.Exchanger_old;
import com.hse.dfa.backend.controller.dto.exchanger.ExchangeRequestDTO;
import com.hse.dfa.backend.exceptions.contract.UserEthereumAddressAbsentException;
import com.hse.dfa.backend.service.user_info.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ExchangerServiceImpl implements ExchangerService {
    private final Exchanger_old exchanger;
    private final UserService userService;

    @Override
    public void addExchangeRequest(ExchangeRequestDTO dto) throws Exception {
        final var user = userService.getCurrentUser();
        exchanger.addExchangeRequest(
            user.getAddress().orElseThrow(
                () -> new UserEthereumAddressAbsentException(
                    format(
                        "User with username = %s dont have ethereum address!",
                        user.getUsername()
                    )
                )
            ),
            dto.getDfaToGet(),
            BigInteger.valueOf(dto.getAmountToGet()),
            dto.getDfaToGive(),
            BigInteger.valueOf(dto.getAmountToGive())
        ).send();
    }
}
