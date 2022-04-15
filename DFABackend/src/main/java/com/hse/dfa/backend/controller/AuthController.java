package com.hse.dfa.backend.controller;

import com.hse.dfa.backend.controller.dto.authentication.TokenResponseDTO;
import com.hse.dfa.backend.controller.dto.user_info.UserInfoForCreateDTO;
import com.hse.dfa.backend.service.authentication.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.hse.dfa.backend.util.converters.authentication.TokenResponseConverter.toTokenResponseDTO;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@SecurityRequirements
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Get fresh jwt token by username and password.")
    @GetMapping(value = "/signIn")
    public TokenResponseDTO signIn(@Parameter(description = "User's nickname.")
                                   @RequestParam
                                   @NotNull
                                   @NotBlank String nickname,
                                   @Parameter(description = "User's password.")
                                   @RequestParam
                                   @NotNull
                                   @NotBlank String password) {
        final var response = authService.generateAuthToken(nickname, password);
        return toTokenResponseDTO(response);
    }

    @Operation(summary = "Create new account and return fresh jwt token.")
    @PostMapping(value = "/signUp")
    public TokenResponseDTO signUp(@Parameter(description = "Info for create new account.")
                                   @RequestBody
                                   @Valid
                                   @NotNull UserInfoForCreateDTO dto) {
        authService.createNewAccount(dto);
        final var response = authService.generateAuthToken(
                dto.getUsername(), dto.getPassword()
        );
        return toTokenResponseDTO(response);
    }
}
