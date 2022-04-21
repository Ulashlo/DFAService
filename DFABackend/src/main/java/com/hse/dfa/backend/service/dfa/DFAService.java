package com.hse.dfa.backend.service.dfa;

import com.hse.dfa.backend.controller.dto.dfa.DFABalanceDTO;
import com.hse.dfa.backend.controller.dto.dfa.DFAInfoForCreateDTO;
import com.hse.dfa.backend.controller.dto.dfa.DFAViewDTO;

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
    void createDFA(DFAInfoForCreateDTO dto) throws Exception;

    /**
     * Return amount of dfa for authenticated user.
     *
     * @param dfaAddress address of smart-contract
     * @return amount of dfa
     */
    Long getBalance(String dfaAddress) throws Exception;

    /**
     * Return amount of dfa for authenticated user for all existing dfa.
     *
     * @return amount of dfa for all existing dfa
     */
    List<DFABalanceDTO> getBalances() throws Exception;

    /**
     * Return all existing in system dfa.
     *
     * @return dfa addresses for all dfa
     */
    List<DFAViewDTO> getAllDfa() throws Exception;
}
