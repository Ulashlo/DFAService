package com.hse.dfa.backend.controller.dto.admin_requests;

import com.hse.dfa.backend.controller.dto.user_info.UserLinkDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(title = "Issuer request", description = "Info for request for issuer rights.")
public class IssuerRequestDTO {
    @NotNull
    @Schema(title = "Request id", description = "Id of request.", implementation = String.class)
    private Long id;
    @NotNull
    @Schema(title = "User", description = "User link info.")
    private UserLinkDTO userLink;
    @NotNull
    @Schema(title = "Date created", description = "Date when request was created.")
    private OffsetDateTime dateCreated;
}
