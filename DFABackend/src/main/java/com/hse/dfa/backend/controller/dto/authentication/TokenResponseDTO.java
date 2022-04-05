package com.hse.dfa.backend.controller.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TokenResponseDTO {
    @NotNull
    private String token;
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotEmpty
    private List<String> roles;
}
