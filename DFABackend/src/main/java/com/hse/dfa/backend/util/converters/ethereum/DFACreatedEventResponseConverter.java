package com.hse.dfa.backend.util.converters.ethereum;

import com.hse.dfa.backend.contracts.Factory;
import com.hse.dfa.backend.model.ethereum.DFACreatedEvent;
import lombok.NoArgsConstructor;

import java.util.Locale;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DFACreatedEventResponseConverter {
    public static DFACreatedEvent toDFACreatedEvent(Factory.DFACreatedEventResponse event) {
        return new DFACreatedEvent(
            event.whoCreate,
            event.dfaAddress.toLowerCase(Locale.ROOT),
            event.name,
            event.symbol,
            event.initialSupply.longValue(),
            event.log.getBlockNumber().longValue()
        );
    }
}
