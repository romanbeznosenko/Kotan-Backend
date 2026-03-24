package com.kotanapp.kotanappapi.modules.admin.club.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClubAdminRequest(
        @NotBlank
        @Schema(description = "Club's name", example = "Kotan Ozorków")
        String name,

        @NotBlank
        @Schema(description = "Club's short name", example = "Kotan")
        String shortName,

        @NotBlank
        @Schema(description = "Club's city", example = "Ozorków")
        String city,

        @NotBlank
        @Schema(description = "Club's country", example = "Poland")
        String country,

        @NotNull
        @Schema(description = "Is our club flag", example = "false")
        Boolean isOurClub
) {
}
