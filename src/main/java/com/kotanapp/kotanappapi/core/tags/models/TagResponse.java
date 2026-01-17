package com.kotanapp.kotanappapi.core.tags.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record TagResponse(
        @Schema(description = "Tag's assigned id", example = "1")
        UUID id,

        @Schema(description = "Tag's name", example = "KOTAN GIRLS")
        String name
) {
}
