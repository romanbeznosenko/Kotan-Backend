package com.kotanapp.kotanappapi.core.tags.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record TagRequest(
        @NotBlank
        @Schema(description = "Tag's name", example = "KOTAN GIRLS")
        String name
) {
}
