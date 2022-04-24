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
@Schema(title = "All requests", description = "All dfa requests.")
public class AllRequestsDTO {
    @NotNull
    @NotEmpty
    @Schema(title = "Dfa address", description = "Dfa to give address.")
    private String dfaToGiveAddress;
    @NotNull
    @Schema(title = "Requests info", description = "Requests info.")
    private List<RequestsForDFADTO> requestsInfo;
}
