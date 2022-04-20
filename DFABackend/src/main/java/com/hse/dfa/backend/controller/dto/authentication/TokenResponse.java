package com.hse.dfa.backend.controller.dto.authentication;

import com.hse.dfa.backend.model.user_info.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TokenResponse {
    @NotNull
    private String token;
    @NotNull
    private User userDetails;
}
