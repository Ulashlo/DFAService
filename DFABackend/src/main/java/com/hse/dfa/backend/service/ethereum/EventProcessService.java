package com.hse.dfa.backend.service.ethereum;

import com.hse.dfa.backend.repository.ethereum.ExchangeCompletedEventRepository;
import com.hse.dfa.backend.service.dfa.DFAService;
import com.hse.dfa.backend.util.contracts.ContractFabric;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.Locale;

import static com.hse.dfa.backend.util.converters.ethereum.ExchangeCompletedEventResponseConverter.toExchangeCompletedEvent;

@Service
@RequiredArgsConstructor
public class EventProcessService {
    private final ContractFabric contractFabric;
    private final DFAService dfaService;
    private final ExchangeCompletedEventRepository exchangeCompletedEventRepository;

    @PostConstruct
    private void processExchangeCompletedEvents() throws Exception {
        final var dfaList = dfaService.getAllDfa();
        for (var dfa : dfaList) {
            processExchangeCompletedEvents(dfa.getAddress());
        }
    }

    @Transactional
    public void processExchangeCompletedEvents(String dfaAddress) throws Exception {
        final var factory = contractFabric.loadFactory();
        final var exchangerAddress = factory
            .getExchanger(dfaAddress).send();
        final var exchanger = contractFabric.loadExchanger(exchangerAddress);
        exchanger.exchangeCompletedEventFlowable(
            DefaultBlockParameter.valueOf(
                BigInteger.valueOf(
                    exchangeCompletedEventRepository
                        .getMaxBlockNum(dfaAddress.toLowerCase(Locale.ROOT)).orElse(-1L) + 1
                )
            ),
            DefaultBlockParameterName.LATEST
        ).forEach(event -> exchangeCompletedEventRepository.saveAndFlush(
            toExchangeCompletedEvent(event)
        ));
    }
}
