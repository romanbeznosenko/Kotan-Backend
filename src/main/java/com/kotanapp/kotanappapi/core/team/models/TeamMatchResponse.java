package com.kotanapp.kotanappapi.core.team.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record TeamMatchResponse(
        @Schema(description = "Team's name", example = "Kotan Ozorków")
        String name,

        @Schema(description = "Team's logo", example = "https://example.com/example.png")
        String logo
) {
}
