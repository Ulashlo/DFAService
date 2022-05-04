package com.hse.dfa.backend.controller;

import com.hse.dfa.backend.controller.dto.admin_requests.IssuerRequestDTO;
import com.hse.dfa.backend.service.request.RequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.hse.dfa.backend.model.user_info.RoleName.ADMIN;

@RestController
@RequestMapping("/safe/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @Operation(summary = "Add request for issuer rights.")
    @PutMapping("/issuer")
    public void addIssuerRequest() {
        requestService.addIssuerRequest();
    }

    @Operation(summary = "Return all created requests for issuer rights.")
    @RolesAllowed(ADMIN)
    @GetMapping("/issuer/created")
    public List<IssuerRequestDTO> getCreatedIssuerRequest() {
        return requestService.getAllCreatedIssuerRequest();
    }

    @Operation(summary = "Accept request for issuer rights.")
    @RolesAllowed(ADMIN)
    @PostMapping("/issuer/accept/{requestId}")
    public void acceptIssuerRequest(
        @PathVariable
        @Schema(title = "Request id", description = "Request for accepting id")
        @NotNull Long requestId) throws Exception {
        requestService.acceptIssuerRequest(requestId);
    }

    @Operation(summary = "Cancel request for issuer rights.")
    @RolesAllowed(ADMIN)
    @PostMapping("/issuer/cansel/{requestId}")
    public void canselIssuerRequest(
        @PathVariable
        @Schema(title = "Request id", description = "Request for cancelling id")
        @NotNull Long requestId) {
        requestService.cancelIssuerRequest(requestId);
    }
}
