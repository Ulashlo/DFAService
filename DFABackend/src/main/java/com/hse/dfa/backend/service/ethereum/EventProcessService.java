package com.hse.dfa.backend.service.ethereum;

import com.hse.dfa.backend.repository.ethereum.DFACreatedEventRepository;
import com.hse.dfa.backend.repository.ethereum.ExchangeCompletedEventRepository;
import com.hse.dfa.backend.util.contracts.ContractFabric;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.Locale;

import static com.hse.dfa.backend.util.converters.ethereum.DFACreatedEventResponseConverter.toDFACreatedEvent;
import static com.hse.dfa.backend.util.converters.ethereum.ExchangeCompletedEventResponseConverter.toExchangeCompletedEvent;

@Service
@RequiredArgsConstructor
public class EventProcessService {
    private final ContractFabric contractFabric;
    private final ExchangeCompletedEventRepository exchangeCompletedEventRepository;
    private final DFACreatedEventRepository dfaCreatedEventRepository;

    @PostConstruct
    private void processDFACreatedEvents() {
        final var factory = contractFabric.loadFactory();
        factory.dFACreatedEventFlowable(
            DefaultBlockParameter.valueOf(
                BigInteger.valueOf(
                    dfaCreatedEventRepository
                        .getMaxBlockNum().orElse(-1L) + 1
                )
            ),
            DefaultBlockParameterName.LATEST
        ).forEach(event -> {
            dfaCreatedEventRepository.saveAndFlush(
                toDFACreatedEvent(event)
            );
            processExchangeCompletedEvents(event.dfaAddress);
        });
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
