package com.hse.dfa.backend.controller.dto.dfa;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(title = "Balance", description = "Users balance info.")
public class DFABalanceDTO {
    @NotNull
    @NotEmpty
    @Schema(title = "Address", description = "Dfa address.")
    private String address;
    @NotNull
    @Schema(title = "Balance", description = "Users balance.")
    private Long balance;
}
