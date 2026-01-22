package com.kotanapp.kotanappapi.core.player.models;

import com.kotanapp.kotanappapi.utils.enums.PlayerPositionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PlayerRequest(
        @Schema(description = "Player's first name", example = "Jan")
        @NotBlank
        String firstName,

        @Schema(description = "Player's last name", example = "Kowalski")
        @NotBlank
        String lastName,

        @Schema(description = "Player's position", example = "STRIKER")
        @NotNull
        PlayerPositionEnum playerPosition,

        @Schema(description = "Player's field number", example = "1")
        String fieldNumber,

        @Schema(description = "Player's birth date", example = "2010-10-10")
        @NotNull
        LocalDate birthDate
) {
}
