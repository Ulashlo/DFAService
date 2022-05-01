package com.hse.dfa.backend.controller.dto.user_info;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(title = "User link", description = "User link info.")
public class UserLinkDTO {
    @NotNull
    @NotEmpty
    @Schema(title = "Username", description = "Username.")
    private String username;
    @NotNull
    @NotEmpty
    @Schema(title = "Email", description = "Email.")
    private String email;
}
