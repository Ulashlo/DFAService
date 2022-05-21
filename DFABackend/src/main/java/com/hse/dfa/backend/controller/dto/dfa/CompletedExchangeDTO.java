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
@Schema(title = "Completed exchange", description = "All completed exchange.")
public class CompletedExchangeDTO {
    @NotNull
    @Schema(title = "Id", description = "Id of exchange.")
    private long id;
    @NotNull
    @NotEmpty
    @Schema(title = "Dfa to get address", description = "Dfa to get address.")
    private String dfaToGetAddress;
    @NotNull
    @Schema(title = "Amount to get", description = "Amount to get.")
    private Long amountToGet;
    @NotNull
    @NotEmpty
    @Schema(title = "Buyer", description = "User who buy DFA.")
    private String buyer;
    @NotNull
    @NotEmpty
    @Schema(title = "Dfa to give address", description = "Dfa to give address.")
    private String dfaToGiveAddress;
    @NotNull
    @Schema(title = "Amount to give", description = "Amount to give.")
    private Long amountToGive;
}
