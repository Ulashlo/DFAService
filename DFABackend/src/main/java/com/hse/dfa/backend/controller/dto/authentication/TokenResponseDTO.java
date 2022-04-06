package com.hse.dfa.backend.controller.dto.authentication;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(title = "Token response", description = "Response with jwt token and user info.")
public class TokenResponseDTO {
    @NotNull
    @Schema(title = "JwtToken", description = "Jwt token.")
    private String token;
    @NotNull
    @NotBlank
    @Schema(title = "Username", description = "Username.")
    private String username;
    @NotNull
    @NotEmpty
    @ArraySchema(schema = @Schema(title = "Role", description = "User's role."))
    private List<String> roles;
}
