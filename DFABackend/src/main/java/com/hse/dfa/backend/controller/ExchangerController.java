package com.hse.dfa.backend.controller;

import com.hse.dfa.backend.controller.dto.dfa.AllRequestsDTO;
import com.hse.dfa.backend.controller.dto.dfa.CompletedExchangeDTO;
import com.hse.dfa.backend.controller.dto.exchanger.ExchangeRequestDTO;
import com.hse.dfa.backend.model.user_info.RoleName;
import com.hse.dfa.backend.service.dfa.DFAService;
import com.hse.dfa.backend.service.exchanger.ExchangerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.hse.dfa.backend.model.user_info.RoleName.TRADER;

@RestController
@RequestMapping("/safe/exchange")
@RequiredArgsConstructor
public class ExchangerController {
    private final ExchangerService exchangerService;
    private final DFAService dfaService;

    @Operation(summary = "Put exchange request to the exchanger.")
    @PutMapping
    @RolesAllowed(TRADER)
    public void addExchange(@Parameter(description = "Info for creation request to exchange.")
                            @RequestBody
                            @NotNull ExchangeRequestDTO dto) throws Exception {
        exchangerService.addExchangeRequest(dto);
    }

    @Operation(summary = "Return all exchange requests.")
    @GetMapping
    public List<AllRequestsDTO> getAllExchanges() throws Exception {
        return exchangerService.getAllRequests();
    }

    @Operation(summary = "Return all completed exchanges for current user.")
    @GetMapping("/completed")
    public List<CompletedExchangeDTO> getAllCompletedExchanges() {
        return dfaService.getCompletedExchanges();
    }
}
