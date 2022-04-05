package com.hse.dfa.backend.controller.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TokenResponse {
    @NotNull
    private String token;
    @NotNull
    private UserDetails userDetails;
}
