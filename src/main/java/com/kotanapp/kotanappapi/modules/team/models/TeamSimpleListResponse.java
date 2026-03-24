package com.kotanapp.kotanappapi.modules.team.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record TeamSimpleListResponse(
        @Schema(description = "Assigned id", example = "1")
        UUID id,

        @Schema(description = "Team's name", example = "Seniorzy")
        String name
) {
}
