package com.hse.dfa.backend.controller.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@NoArgsConstructor
@Getter
@Schema(title = "ApiError", description = "Description of error, which was happened on server.")
public class ApiError {
    @NotNull
    @Schema(implementation = String.class, title = "ErrorTime", description = "Error time")
    private OffsetDateTime errorDateTime;
    @NotNull
    private int status;
    @NotNull
    private String message;

    public static ApiError createError(HttpStatus status, String message) {
        return new ApiError(status, message);
    }

    protected ApiError(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
        this.errorDateTime = OffsetDateTime.now();
    }
}
