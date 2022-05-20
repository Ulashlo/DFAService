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
    @Schema(title = "Id", description = "Id.")
    private long id;
    @NotNull
    @NotEmpty
    @Schema(title = "First user address", description = "First user address.")
    private String firstUserAddress;
    @NotNull
    @NotEmpty
    @Schema(title = "First dfa address", description = "First dfa address.")
    private String firstDfaAddress;
    @NotNull
    @Schema(title = "First amount", description = "First amount.")
    private Long firstAmount;
    @NotNull
    @NotEmpty
    @Schema(title = "Second user address", description = "Second user address.")
    private String secondUserAddress;
    @NotNull
    @NotEmpty
    @Schema(title = "Second dfa address", description = "Second dfa address.")
    private String secondDfaAddress;
    @NotNull
    @Schema(title = "Second amount", description = "Second amount.")
    private Long secondAmount;
}
