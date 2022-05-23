package com.hse.dfa.backend.controller.dto.exchanger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(title = "Info for delete request", description = "Info for delete exchange request.")
public class ExchangeInfoForDeleteDTO {
    @NotNull
    @NotEmpty
    @Schema(title = "Dfa to give", description = "Dfa to give address.")
    private String dfaToGiveAddress;
    @NotNull
    @NotEmpty
    @Schema(title = "Dfa to get address", description = "Dfa to get address.")
    private String dfaToGetAddress;
    @NotNull
    @Schema(title = "Dfa index", description = "Index of dfa.")
    private Long index;
}
