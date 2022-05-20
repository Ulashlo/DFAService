package com.hse.dfa.backend.util.converters.ethereum;

import com.hse.dfa.backend.contracts.Exchanger;
import com.hse.dfa.backend.contracts.Exchanger.ExchangeCompletedEventResponse;
import com.hse.dfa.backend.model.ethereum.ExchangeCompletedEvent;
import lombok.NoArgsConstructor;

import java.util.Locale;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ExchangeCompletedEventResponseConverter {
    public static ExchangeCompletedEvent toExchangeCompletedEvent(ExchangeCompletedEventResponse event) {
        return new ExchangeCompletedEvent(
            event.firstUser.toLowerCase(Locale.ROOT),
            event.firstDfa.toLowerCase(Locale.ROOT),
            event.firstAmount.longValue(),
            event.secondUser.toLowerCase(Locale.ROOT),
            event.secondDfa.toLowerCase(Locale.ROOT),
            event.secondAmount.longValue(),
            event.log.getBlockNumber().longValue()
        );
    }
}
