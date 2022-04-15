package com.hse.dfa.backend.service.exchanger;

import com.hse.dfa.backend.controller.dto.exchanger.ExchangeRequestDTO;

/**
 * Service that provide interaction with exchanger
 */
public interface ExchangerService {
    /**
     * Generate new jwt token.
     *
     * @param exchangeRequest info for creation request to exchange
     */
    void addExchangeRequest(ExchangeRequestDTO exchangeRequest) throws Exception;
}
