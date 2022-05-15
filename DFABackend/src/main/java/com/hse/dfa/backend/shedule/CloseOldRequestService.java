package com.hse.dfa.backend.shedule;

import com.hse.dfa.backend.util.contracts.ContractFabric;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableAsync
public class CloseOldRequestService {
    private final ContractFabric contractFabric;

    @Async
    @Scheduled(fixedRate = 60000)
    public void closeOldRequest() throws Exception {
        final var factory = contractFabric.loadFactory();
        factory.closeOldRequests().send();
    }
}
