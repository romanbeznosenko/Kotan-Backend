package com.kotanapp.kotanappapi.modules.club.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ClubListResponse(
        @Schema(description = "Assigned id", example = "1")
        UUID id,

        @Schema(description = "Club's name", example = "Górnik Łęczyca")
        String name,

        @Schema(description = "Club's logo", example = "https://example.com/example.png")
        String logo
) {
}
