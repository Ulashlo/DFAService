package com.hse.dfa.backend.util.converters.user;

import com.hse.dfa.backend.controller.dto.user_info.UserViewDTO;
import com.hse.dfa.backend.model.user_info.User;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserConverter {
    public static UserViewDTO toUserViewDTO(User user) {
        return new UserViewDTO(
            user.getUsername(),
            user.getAddress().orElse(""),
            user.getPrivateKey().orElse("")
        );
    }
}
