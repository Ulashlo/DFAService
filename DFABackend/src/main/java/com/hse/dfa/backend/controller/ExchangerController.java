package com.hse.dfa.backend.controller;

import com.hse.dfa.backend.controller.dto.dfa.AllRequestsDTO;
import com.hse.dfa.backend.controller.dto.exchanger.ExchangeRequestDTO;
import com.hse.dfa.backend.service.exchanger.ExchangerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/safe/exchange")
@RequiredArgsConstructor
public class ExchangerController {
    private final ExchangerService exchangerService;

    @Operation(summary = "Put exchange request to the exchanger.")
    @PutMapping
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
}
