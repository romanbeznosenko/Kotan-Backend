package com.kotanapp.kotanappapi.core.login.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record RefreshTokenResponse(
        @Schema(description = "New access token")
        String jwt,

        @Schema(description = "New refresh token")
        String refreshToken
) {
}
