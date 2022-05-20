package com.hse.dfa.backend.util.converters.contract;

import com.hse.dfa.backend.controller.dto.dfa.CompletedExchangeDTO;
import com.hse.dfa.backend.controller.dto.dfa.DFACostDTO;
import com.hse.dfa.backend.model.ethereum.ExchangeCompletedEvent;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DFAConverter {
    public static CompletedExchangeDTO toCompletedExchangeDTO(ExchangeCompletedEvent event) {
        return new CompletedExchangeDTO(
            event.getId(),
            event.getFirstUserAddress(),
            event.getFirstDfaAddress(),
            event.getFirstAmount(),
            event.getSecondUserAddress(),
            event.getSecondDfaAddress(),
            event.getSecondAmount()
        );
    }
}
