package com.hse.dfa.backend.service.dfa;

import com.hse.dfa.backend.contracts.Exchanger.ExchangeCompletedEventResponse;
import com.hse.dfa.backend.controller.dto.dfa.DFABalanceDTO;
import com.hse.dfa.backend.controller.dto.dfa.DFACostDTO;
import com.hse.dfa.backend.controller.dto.dfa.DFAInfoForCreateDTO;
import com.hse.dfa.backend.controller.dto.dfa.DFAViewDTO;
import com.hse.dfa.backend.service.user_info.UserService;
import com.hse.dfa.backend.util.checkers.UserChecker;
import com.hse.dfa.backend.util.contracts.ContractFabric;
import com.hse.dfa.backend.util.converters.contract.DFAInfoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hse.dfa.backend.util.checkers.UserChecker.checkAddress;
import static com.hse.dfa.backend.util.checkers.UserChecker.checkPrivateKey;
import static com.hse.dfa.backend.util.converters.contract.DFAInfoConverter.tupleToDFAViewDTO;

@Service
@RequiredArgsConstructor
public class DFAServiceImpl implements DFAService {
    private final UserService userService;
    private final ContractFabric contractFabric;
    private final Web3j web3j;

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
    public List<DFACostDTO> getDfaCosts(String dfaAddress) throws Exception {
        final var factory = contractFabric.loadFactory();
        final var exchangerAddress = factory.getExchanger(dfaAddress).send();
        final var exchanger = contractFabric.loadExchanger(exchangerAddress);
        return exchanger.exchangeCompletedEventFlowable(
                DefaultBlockParameter.valueOf(BigInteger.ZERO),
                DefaultBlockParameter.valueOf(
                    web3j.ethBlockNumber().send().getBlockNumber()
                )
            ).toList()
            .blockingGet()
            .stream()
            .collect(Collectors.groupingBy((ExchangeCompletedEventResponse event) -> event.secondDfa))
            .entrySet().stream()
            .map((Map.Entry<String, List<ExchangeCompletedEventResponse>> entry) -> new DFACostDTO(
                entry.getKey(),
                entry.getValue().stream()
                    .mapToDouble((ExchangeCompletedEventResponse ex) -> ex.secondAmount.doubleValue())
                    .average().orElse(-1)
            ))
            .filter((DFACostDTO cost) -> cost.getCost() > 0)
            .collect(Collectors.toList());
    }
}
