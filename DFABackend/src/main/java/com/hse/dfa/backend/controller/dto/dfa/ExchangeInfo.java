package com.hse.dfa.backend.controller.dto.dfa;

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
@Schema(title = "Request", description = "Request info.")
public class ExchangeInfo {
    @NotNull
    @NotEmpty
    @Schema(title = "User", description = "User address.")
    private String userAddress;
    @NotNull
    @Schema(title = "Amount to give", description = "Amount dfa to give.")
    private Long amountToGive;
    @NotNull
    @Schema(title = "Amount to get", description = "Amount dfa to get.")
    private Long amountToGet;
    @NotNull
    @Schema(title = "Exchange type", description = "Type of exchange.")
    private ExchangeType exchangeType;
    @NotNull
    @Schema(title = "Index", description = "Index of exchange.")
    private Long index;
}
