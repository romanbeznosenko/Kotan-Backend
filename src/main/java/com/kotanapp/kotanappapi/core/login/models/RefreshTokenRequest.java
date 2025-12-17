package com.kotanapp.kotanappapi.core.login.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RefreshTokenRequest(
        @NotBlank
        @Schema(description = "Refresh token JWT")
        String refreshToken
) {
}
