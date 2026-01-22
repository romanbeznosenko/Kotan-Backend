package com.kotanapp.kotanappapi.core.player.models;

import com.kotanapp.kotanappapi.utils.enums.PlayerPositionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record PlayerResponse(
        @Schema(description = "Assigned id", example = "1")
        UUID playerId,

        @Schema(description = "Player's first name", example = "Jan")
        String firstName,

        @Schema(description = "Player's last name", example = "Kowalski")
        String lastName,

        @Schema(description = "Player's position", example = "STRIKER")
        PlayerPositionEnum position,

        @Schema(description = "Player's birth date", example = "10-10-2026")
        LocalDate birthDate,

        @Schema(description = "Player's avatar", example = "https://example.com/example.png")
        String avatar,

        @Schema(description = "Player's field number", example = "1")
        String fieldNumber
) {
}
