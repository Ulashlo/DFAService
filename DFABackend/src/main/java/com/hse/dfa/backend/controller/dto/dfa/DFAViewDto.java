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
@Schema(title = "Dfa info", description = "Info for presenting dfa.")
public class DFAViewDto {
    @NotNull
    @NotEmpty
    @Schema(title = "Address", description = "Dfa address.")
    private String address;
    @NotNull
    @NotEmpty
    @Schema(title = "Owner", description = "Dfa owner address.")
    private String owner;
    @NotNull
    @NotEmpty
    @Schema(title = "Name", description = "Dfa name.")
    private String name;
    @NotNull
    @NotEmpty
    @Schema(title = "Symbol", description = "Dfa symbol.")
    private String symbol;
    @NotNull
    @Schema(title = "Total supply", description = "Initial supply for dfa.")
    private Long totalSupply;
}
