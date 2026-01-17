package com.kotanapp.kotanappapi.core.news.models;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

public record NewsResponse(
        @Schema(description = "News' title", example = "KOTAN GIRLS | ...")
        String title,

        @Schema(description = "News' content", example = "Dzisiaj cztery ...")
        String content,

        @Schema(description = "News' banner", example = "https://example.com/example.png")
        String banner,

        @Schema(description = "News' creation timestamp")
        Instant createdAt
) {
}
