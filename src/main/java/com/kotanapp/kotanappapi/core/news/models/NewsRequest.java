package com.kotanapp.kotanappapi.core.news.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record NewsRequest(
        @NotBlank
        @Schema(description = "News' title", example = "KOTAN GIRLS | ...")
        String title,

        @NotBlank
        @Schema(description = "News' short description", example = "Dzisiaj cztery ...")
        String shortDescription,

        @NotBlank
        @Schema(description = "News' content", example = "Dzisiaj cztery zawodzniczki ...")
        String content
) {
}
