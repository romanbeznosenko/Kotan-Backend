package com.kotanapp.kotanappapi.core.login.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record LoginResponse(
        @Schema(description = "Access token (JWT)")
        String jwt,

        @Schema(description = "Refresh token (JWT)")
        String refreshToken) {
}
