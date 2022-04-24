package com.hse.dfa.backend.controller.dto.dfa;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(title = "Requests for DFA", description = "All requests for one DFA.")
public class RequestsForDFADTO {
    @NotNull
    @NotEmpty
    @Schema(title = "Dfa address", description = "Dfa to get address.")
    private String dfaToGetAddress;
    @NotNull
    @Schema(title = "Exchange infos", description = "Exchange infos.")
    private List<ExchangeInfo> exchangeInfos;
}
