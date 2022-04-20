package com.hse.dfa.backend.service.dfa;

import com.hse.dfa.backend.controller.dto.dfa.DFAInfoForCreateDTO;
import com.hse.dfa.backend.controller.dto.dfa.DFAViewDto;
import com.hse.dfa.backend.service.user_info.UserService;
import com.hse.dfa.backend.util.contracts.ContractFabric;
import com.hse.dfa.backend.util.converters.contract.DFAInfoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

import static com.hse.dfa.backend.util.checkers.UserChecker.checkAddress;
import static com.hse.dfa.backend.util.checkers.UserChecker.checkPrivateKey;

@Service
@RequiredArgsConstructor
public class DFAServiceImpl implements DFAService {
    private final UserService userService;
    private final ContractFabric contractFabric;

    @Override
    public void createDFA(DFAInfoForCreateDTO dto) throws Exception {
        final var currentUser = userService.getCurrentUser();
        final var privateKey = checkPrivateKey(currentUser);
        final var factory = contractFabric.loadFactory(privateKey);
        factory.createDfa(
            BigInteger.valueOf(dto.getInitialSupply()),
            dto.getName(),
            dto.getSymbol()
        ).send();
    }

    @Override
    public Long getBalance(String dfaAddress) throws Exception {
        final var user = userService.getCurrentUser();
        final var userAddress = checkAddress(user);
        final var dfa = contractFabric.loadDfa(dfaAddress);
        return dfa.balanceOf(userAddress).send().longValue();
    }

    @Override
    public List<DFAViewDto> getAllDfa() throws Exception {
        final var factory = contractFabric.loadFactory();
        return DFAInfoConverter.tupleToDFAViewDto(
            factory.getAllDfa().send()
        );
    }
}
