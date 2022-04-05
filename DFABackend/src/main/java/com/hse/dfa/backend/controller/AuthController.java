package com.hse.dfa.backend.controller;

import com.hse.dfa.backend.controller.dto.authentication.TokenResponseDTO;
import com.hse.dfa.backend.controller.dto.authentication.user_info.UserInfoForCreateDTO;
import com.hse.dfa.backend.service.authentication.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.hse.dfa.backend.util.converters.authentication.TokenResponseConverter.toTokenResponseDTO;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping(value = "/signIn")
    public TokenResponseDTO signIn(@RequestParam String nickname, @RequestParam String password) {
        final var response = authService.generateAuthToken(nickname, password);
        return toTokenResponseDTO(response);
    }

    @PostMapping(value = "/signUp")
    public TokenResponseDTO signUp(@RequestBody UserInfoForCreateDTO dto) {
        authService.createNewAccount(dto);
        final var response = authService.generateAuthToken(
            dto.getUsername(), dto.getPassword()
        );
        return toTokenResponseDTO(response);
    }
}
