package com.hse.dfa.backend.controller;

import com.hse.dfa.backend.controller.dto.exchanger.ExchangeRequestDTO;
import com.hse.dfa.backend.service.exchanger.ExchangerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/safe")
@RequiredArgsConstructor
public class ExchangerController {
    private final ExchangerService exchangerService;

    @Operation(summary = "Put exchange request to the exchanger.")
    @PutMapping(value = "/exchange")
    public void addExchange(@Parameter(description = "Info for creation request to exchange.")
                            @RequestBody
                            @NotNull ExchangeRequestDTO dto) throws Exception {
        exchangerService.addExchangeRequest(dto);
    }

    @Operation(summary = "Put exchange request to the exchanger.")
    @GetMapping(value = "/exchange")
    public Object test(@RequestParam String dtoToGet,
                     @RequestParam String dtoToGive,
                     @RequestParam Long num) throws Exception {
        return null;
    }
}
