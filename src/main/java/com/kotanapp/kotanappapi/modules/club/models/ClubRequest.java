package com.kotanapp.kotanappapi.modules.club.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClubRequest(
        @NotBlank
        @Schema(description = "Club's name", example = "Górnik Łęczyca")
        String name,

        @NotBlank
        @Schema(description = "Club's short name", example = "Górnik")
        String shortName,

        @NotBlank
        @Schema(description = "Club's city", example = "Łęczyca")
        String city,

        @NotBlank
        @Schema(description = "Club's country", example = "Poland")
        String country,

        @NotNull
        @Schema(description = "Is out club flag", example = "false")
        Boolean isOurClub
) {
}
