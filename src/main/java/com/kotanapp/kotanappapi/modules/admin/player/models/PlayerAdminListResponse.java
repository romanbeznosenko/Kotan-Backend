package com.kotanapp.kotanappapi.modules.admin.player.models;

import com.kotanapp.kotanappapi.utils.enums.PositionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PlayerAdminListResponse(
        @Schema(description = "Assigned id", example = "1")
        UUID id,

        @Schema(description = "Player's first name", example = "Jan")
        String firstName,

        @Schema(description = "Player's last name", example = "Kowalski")
        String lastName,

        @Schema(description = "Player's jersey number", example = "1")
        String jerseyNumber,

        @Schema(description = "Player's position", example = "GOALKEEPER")
        PositionEnum position,

        @Schema(description = "Player's photo", example = "https://example.com/example.png")
        String photo
) {
}
