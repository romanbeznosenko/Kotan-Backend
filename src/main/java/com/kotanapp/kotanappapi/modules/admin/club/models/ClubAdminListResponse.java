package com.kotanapp.kotanappapi.modules.admin.club.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ClubAdminListResponse(
        @Schema(description = "Assigned id", example = "1")
        UUID id,

        @Schema(description = "Club's logo", example = "https://example.com/example.png")
        String logo,

        @Schema(description = "Club's name", example = "Kotan Ozorków")
        String name,

        @Schema(description = "Club's short name", example = "Kotan")
        String shortName,

        @Schema(description = "Club's city", example = "Ozorków")
        String city
) {
}
