package com.kotanapp.kotanappapi.modules.admin.player.models;

import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
import com.kotanapp.kotanappapi.utils.enums.PositionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PlayerAdminRequest(
        @NotBlank
        @Schema(description = "Player's first name", example = "Jan")
        String firstName,

        @NotBlank
        @Schema(description = "Player's last name", example = "Kowalski")
        String lastName,

        @NotNull
        @Schema(description = "Player's position", example = "GOALKEEPER")
        PositionEnum position,

        @NotNull
        @Schema(description = "Player's gender", example = "MEN")
        GenderEnum gender,

        @NotBlank
        @Schema(description = "Player's jersey number", example = "1")
        String jerseyNumber,

        @NotNull
        @Schema(description = "Player's birth date")
        LocalDate birthDate
) {
}
