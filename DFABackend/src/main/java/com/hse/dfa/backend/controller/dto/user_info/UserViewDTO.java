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
@Schema(title = "User view", description = "User view info.")
public class UserViewDTO {
    @NotNull
    @NotEmpty
    @Schema(title = "Username", description = "Username.")
    private String username;
    @NotNull
    @Schema(title = "Address", description = "Ethereum address.")
    private String address;
    @NotNull
    @Schema(title = "Private key", description = "Ethereum private key.")
    private String privateKey;
}
