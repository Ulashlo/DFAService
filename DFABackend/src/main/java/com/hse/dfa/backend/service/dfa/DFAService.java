package com.hse.dfa.backend.service.dfa;

import com.hse.dfa.backend.controller.dto.dfa.DFAInfoForCreateDTO;

import java.util.List;

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

    /**
     * Return all existing in system dfa.
     *
     * @return dfa addresses for all dfa
     */
    List<String> getAllDfa() throws Exception;
}
