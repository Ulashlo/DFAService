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
@Schema(title = "DFA cost", description = "Info about dfa cost.")
public class DFACostDTO {
    @NotNull
    @NotEmpty
    @Schema(title = "Address", description = "Dfa address.")
    private String address;
    @NotNull
    @Schema(title = "Cost", description = "Dfas cost.")
    private Double cost;
}
