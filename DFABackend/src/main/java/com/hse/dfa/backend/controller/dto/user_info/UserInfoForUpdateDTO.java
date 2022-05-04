package com.hse.dfa.backend.controller.dto.user_info;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(title = "User info for update", description = "User info for update.")
public class UserInfoForUpdateDTO {
    @Schema(title = "Email", description = "Users email.")
    private String email;
    @Schema(title = "Address", description = "Ethereum address.")
    private String address;
    @Schema(title = "Private key", description = "Ethereum private key.")
    private String privateKey;
}
