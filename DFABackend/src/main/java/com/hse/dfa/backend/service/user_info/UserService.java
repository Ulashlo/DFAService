package com.hse.dfa.backend.service.user_info;

import com.hse.dfa.backend.controller.dto.user_info.UserInfoForUpdateDTO;
import com.hse.dfa.backend.controller.dto.user_info.UserViewDTO;
import com.hse.dfa.backend.model.user_info.User;

/**
 * Service that provide a facade to the current user session.
 */
public interface UserService {
    /**
     * Return current authenticated user.
     *
     * @return current user
     */
    User getCurrentUser();

    /**
     * Return user info.
     *
     * @return current user
     */
    UserViewDTO getCurrentUserInfo();

    /**
     * Update user info.
     */
    void updateUserInfo(UserInfoForUpdateDTO dto);
}
