package com.hse.dfa.backend.service.authentication;

import com.hse.dfa.backend.controller.dto.authentication.TokenResponse;
import com.hse.dfa.backend.controller.dto.user_info.UserInfoForCreateDTO;

/**
 * Service that provide authentication utilities
 */
public interface AuthService {
    /**
     * Generate new jwt token.
     *
     * @param username user's login
     * @param password user's password
     * @return new jwt token
     */
    TokenResponse generateAuthToken(String username, String password);

    /**
     * Create new account.
     *
     * @param dto info for create user
     */
    void createNewAccount(UserInfoForCreateDTO dto);
}
