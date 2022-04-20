package com.hse.dfa.backend.util.converters.authentication;

import com.hse.dfa.backend.controller.dto.authentication.TokenResponse;
import com.hse.dfa.backend.controller.dto.authentication.TokenResponseDTO;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class TokenResponseConverter {
    public static TokenResponseDTO toTokenResponseDTO(TokenResponse tokenResponse) {
        final var details = tokenResponse.getUserDetails();
        return new TokenResponseDTO(
            tokenResponse.getToken(),
            details.getUsername(),
            details.getAddress().orElse(""),
            details.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList()
        );
    }
}
