package com.hse.dfa.backend.service.dfa;

import com.hse.dfa.backend.controller.dto.dfa.DFAInfoForCreateDTO;

/**
 * Service that provide interaction with exchanger
 */
public interface DFAService {
    /**
     * Create new Dfa.
     *
     * @param dto info for creation new dfa
     * @return new dfa address
     */
    String createDFA(DFAInfoForCreateDTO dto) throws Exception;

    /**
     * Return amount of dfa for authenticated user.
     *
     * @param dfaAddress address of smart-contract
     * @return amount of dfa
     */
    Long getBalance(String dfaAddress) throws Exception;
}
