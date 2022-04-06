package com.hse.dfa.backend.controller.dto.authentication.user_info;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(title = "UserInfoForCreateDTO", description = "Info for create new user.")
public class UserInfoForCreateDTO {
    @NotNull
    @NotEmpty
    @Schema(title = "Username", description = "Username.")
    private String username;
    @NotNull
    @NotEmpty
    @Schema(title = "Password", description = "Password.")
    private String password;
}
