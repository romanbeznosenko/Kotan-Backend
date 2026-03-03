package com.kotanapp.kotanappapi.modules.club.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ClubResponse(
        @Schema(description = "Assigned id", example = "1")
        UUID id,

        @Schema(description = "Club's name", example = "Górnik Łęczyca")
        String name,

        @Schema(description = "Club's short name", example = "Górnik")
        String shortName,

        @Schema(description = "Club's city", example = "Łęczyca")
        String city,

        @Schema(description = "Club's country", example = "Polska")
        String country,

        @Schema(description = "Club's logo", example = "https://example.com/example.png")
        String logo,

        @Schema(description = "Club's is out club flag", example = "false")
        Boolean isOurClub
) {
}
