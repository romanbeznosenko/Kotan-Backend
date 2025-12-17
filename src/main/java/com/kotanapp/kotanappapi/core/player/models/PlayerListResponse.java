package com.kotanapp.kotanappapi.core.player.models;

import com.kotanapp.kotanappapi.utils.enums.PlayerPositionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PlayerListResponse(
        @Schema(description = "Assigned id", example = "1")
        UUID id,

        @Schema(description = "Player's first name", example = "Jan")
        String firstName,

        @Schema(description = "Player's last name", example = "Kowalski")
        String lastName,

        @Schema(description = "Player's position", example = "DEFENDER")
        PlayerPositionEnum playerPosition,

        @Schema(description = "Player's field number", example = "1")
        String number
) {
}
