package com.hse.dfa.backend.util.converters.contract;

import com.hse.dfa.backend.controller.dto.dfa.CompletedExchangeDTO;
import com.hse.dfa.backend.model.ethereum.ExchangeCompletedEvent;
import lombok.NoArgsConstructor;

import java.util.Locale;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DFAConverter {
    public static CompletedExchangeDTO toCompletedExchangeDTO(ExchangeCompletedEvent event, String userAddress) {
        if (event.getFirstUserAddress().toLowerCase(Locale.ROOT).equals(userAddress.toLowerCase(Locale.ROOT))) {
            return new CompletedExchangeDTO(
                event.getId(),
                event.getSecondDfaAddress(),
                event.getSecondAmount(),
                event.getSecondUserAddress(),
                event.getFirstDfaAddress(),
                event.getFirstAmount()
            );
        } else {
            return new CompletedExchangeDTO(
                event.getId(),
                event.getFirstDfaAddress(),
                event.getFirstAmount(),
                event.getFirstUserAddress(),
                event.getSecondDfaAddress(),
                event.getSecondAmount()
            );
        }
    }
}
