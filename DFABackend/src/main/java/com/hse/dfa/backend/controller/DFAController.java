package com.hse.dfa.backend.controller;

import com.hse.dfa.backend.controller.dto.dfa.DFABalanceDTO;
import com.hse.dfa.backend.controller.dto.dfa.DFAInfoForCreateDTO;
import com.hse.dfa.backend.controller.dto.dfa.DFAViewDTO;
import com.hse.dfa.backend.model.user_info.RoleName;
import com.hse.dfa.backend.service.dfa.DFAService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.hse.dfa.backend.model.user_info.RoleName.*;

@RestController
@RequestMapping("/safe/dfa")
@RequiredArgsConstructor
public class DFAController {
    private final DFAService dfaService;

    @Operation(summary = "Create new dfa.")
    @PutMapping
    @RolesAllowed(ISSUER)
    public void createDFA(@Parameter(description = "Info for creation new dfa.")
                          @RequestBody
                          @NotNull DFAInfoForCreateDTO dto) throws Exception {
        dfaService.createDFA(dto);
    }

    @Operation(summary = "Return amount of dfa.")
    @GetMapping(value = "/balance")
    @RolesAllowed(TRADER)
    public Long getBalance(@Parameter(description = "Address of dfa.")
                           @RequestParam
                           @NotNull String dfaAddress) throws Exception {
        return dfaService.getBalance(dfaAddress);
    }

    @Operation(summary = "Return amount of dfa for all existing dfa.")
    @GetMapping(value = "/balances")
    @RolesAllowed(TRADER)
    public List<DFABalanceDTO> getBalances() throws Exception {
        return dfaService.getBalances();
    }

    @Operation(summary = "Return all existing in system dfa.")
    @GetMapping
    public List<DFAViewDTO> getAllDfa() throws Exception {
        return dfaService.getAllDfa();
    }
}
