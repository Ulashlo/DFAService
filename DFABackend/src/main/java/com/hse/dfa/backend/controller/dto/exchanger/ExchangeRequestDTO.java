package com.hse.dfa.backend.controller.dto.exchanger;

import com.hse.dfa.backend.contracts.ExchangeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(title = "Exchange request", description = "Info for creation request to exchange.")
public class ExchangeRequestDTO {
    @NotNull
    @Schema(title = "Exchange type", description = "Divisible or indivisible.")
    private ExchangeType type;
    @NotNull
    @NotEmpty
    @Schema(title = "Dfa to get", description = "Dfa, user want to get.")
    private String dfaToGet;
    @NotNull
    @Schema(title = "Amount to get", description = "Amount of dfa, user want to get.")
    private int amountToGet;
    @NotNull
    @NotEmpty
    @Schema(title = "Dfa to give", description = "Dfa, user want to give.")
    private String dfaToGive;
    @NotNull
    @Schema(title = "Amount to give", description = "Amount of dfa, user want to give.")
    private int amountToGive;
    @NotNull
    @Schema(title = "End time", description = "End time.")
    private long endTime;
}
