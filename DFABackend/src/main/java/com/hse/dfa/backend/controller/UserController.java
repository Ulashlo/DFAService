package com.hse.dfa.backend.controller;

import com.hse.dfa.backend.controller.dto.user_info.UserInfoForUpdateDTO;
import com.hse.dfa.backend.controller.dto.user_info.UserViewDTO;
import com.hse.dfa.backend.service.user_info.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/safe/user")
@RequiredArgsConstructor
@SecurityRequirements
public class UserController {
    private final UserService userService;

    @Operation(summary = "Return current user info.")
    @GetMapping
    public UserViewDTO getUserInfo() {
        return userService.getCurrentUserInfo();
    }

    @Operation(summary = "Update current user info.")
    @PutMapping
    public void updateUserInfo(@Parameter(description = "User info for update dto.")
                               @RequestBody
                               @NotNull UserInfoForUpdateDTO dto) {
        userService.updateUserInfo(dto);
    }
}
