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
@Schema(title = "Info for create Dfa", description = "Info for creation new dfa.")
public class DFAInfoForCreateDTO {
    @NotNull
    @NotEmpty
    @Schema(title = "Initial supply", description = "Initial supply fro dfa.")
    private int initialSupply;
    @NotNull
    @NotEmpty
    @Schema(title = "Name", description = "Dfa name.")
    private String name;
    @NotNull
    @NotEmpty
    @Schema(title = "Symbol", description = "Dfa symbol.")
    private String symbol;
}
