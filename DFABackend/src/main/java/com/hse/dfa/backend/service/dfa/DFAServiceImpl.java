package com.hse.dfa.backend.service.dfa;

import com.hse.dfa.backend.controller.dto.dfa.*;
import com.hse.dfa.backend.repository.ethereum.ExchangeCompletedEventRepository;
import com.hse.dfa.backend.service.user_info.UserService;
import com.hse.dfa.backend.util.checkers.UserChecker;
import com.hse.dfa.backend.util.contracts.ContractFabric;
import com.hse.dfa.backend.util.converters.contract.DFAInfoConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.hse.dfa.backend.util.checkers.UserChecker.checkAddress;
import static com.hse.dfa.backend.util.checkers.UserChecker.checkPrivateKey;
import static com.hse.dfa.backend.util.converters.contract.DFAConverter.toCompletedExchangeDTO;
import static com.hse.dfa.backend.util.converters.contract.DFAInfoConverter.tupleToDFAViewDTO;

@Service
@RequiredArgsConstructor
public class DFAServiceImpl implements DFAService {
    private final UserService userService;
    private final ContractFabric contractFabric;
    private final ExchangeCompletedEventRepository exchangeCompletedEventRepository;

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
    public List<DFABalanceDTO> getBalances() throws Exception {
        final var user = userService.getCurrentUser();
        final var privateKey = UserChecker.checkPrivateKey(user);
        final var factory = contractFabric.loadFactory(privateKey);
        final var balances = factory.getBalances().send();
        return DFAInfoConverter.tupleToDFABalanceDTO(balances);
    }

    @Override
    public List<DFAViewDTO> getAllDfa() throws Exception {
        final var factory = contractFabric.loadFactory();
        return tupleToDFAViewDTO(
            factory.getAllDfa().send()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<DFACostDTO> getDfaCosts(String dfaAddress) {
        final var address = dfaAddress.toLowerCase(Locale.ROOT);
        final var dfaVolumes = new HashMap<String, DfaVolume>();
        exchangeCompletedEventRepository.findAllByFirstDfaAddressOrSecondDfaAddress(
                address, address
            ).forEach(event -> {
            if (event.getFirstDfaAddress().equals(address)) {
                dfaVolumes.computeIfAbsent(event.getSecondDfaAddress(), addr -> new DfaVolume());
                final var volume = dfaVolumes.get(event.getSecondDfaAddress());
                volume.addAmountToGive(event.getFirstAmount());
                volume.addAmountToGet(event.getSecondAmount());
            } else if (event.getSecondDfaAddress().equals(address)) {
                dfaVolumes.computeIfAbsent(event.getFirstDfaAddress(), addr -> new DfaVolume());
                final var volume = dfaVolumes.get(event.getFirstDfaAddress());
                volume.addAmountToGive(event.getSecondAmount());
                volume.addAmountToGet(event.getFirstAmount());
            }
        });
        return dfaVolumes.entrySet().stream()
            .map(entry -> new DFACostDTO(
                entry.getKey(),
                entry.getValue().getAmountToGet().doubleValue() /
                    entry.getValue().getAmountToGive().doubleValue()
            )).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompletedExchangeDTO> getCompletedExchanges() {
        final var user = userService.getCurrentUser();
        final var userAddress = UserChecker.checkAddress(user);
        final var address = userAddress.toLowerCase(Locale.ROOT);
        return exchangeCompletedEventRepository.findAllByFirstUserAddressOrSecondUserAddress(
                address, address
            ).stream()
            .map(event -> toCompletedExchangeDTO(event, address))
            .collect(Collectors.toList());
    }

    @Getter
    private static class DfaVolume {
        private Long amountToGive;
        private Long amountToGet;

        public DfaVolume() {
            this.amountToGet = 0L;
            this.amountToGive = 0L;
        }

        public void addAmountToGive(long amount) {
            this.amountToGive += amount;
        }

        public void addAmountToGet(long amount) {
            this.amountToGet += amount;
        }
    }
}
