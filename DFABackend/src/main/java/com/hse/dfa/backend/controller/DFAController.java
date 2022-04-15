package com.hse.dfa.backend.controller;

import com.hse.dfa.backend.controller.dto.dfa.DFAInfoForCreateDTO;
import com.hse.dfa.backend.service.dfa.DFAService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;

import static com.hse.dfa.backend.model.user_info.RoleName.TRADER;

@RestController
@RequestMapping("/safe")
@RequiredArgsConstructor
@RolesAllowed(TRADER)
public class DFAController {
    private final DFAService dfaService;

    @Operation(summary = "Create new dfa.")
    @PutMapping(value = "/dfa")
    public String createDFA(@Parameter(description = "Info for creation new dfa.")
                            @RequestBody
                            @NotNull DFAInfoForCreateDTO dto) throws Exception {
        return dfaService.createDFA(dto);
    }

    @Operation(summary = "Return amount of dfa.")
    @GetMapping(value = "/dfa")
    public Long getBalance(@Parameter(description = "Address of dfa.")
                           @RequestParam
                           @NotNull String dfaAddress) throws Exception {
        return dfaService.getBalance(dfaAddress);
    }
}
