package com.kotanapp.kotanappapi.modules.player.models;

import com.kotanapp.kotanappapi.utils.enums.PositionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PlayerSquadResponse(
        @Schema(description = "Assigned id", example = "1")
        UUID id,

        @Schema(description = "Player's name", example = "Jan Nowak")
        String name,

        @Schema(description = "Player's position", example = "STRIKER")
        PositionEnum position,

        @Schema(description = "Player's jersey number", example = "1")
        String jerseyNumber,

        @Schema(description = "Player's photo", example = "https::/example.com/example.png")
        String photo
) {
}
