package com.kotanapp.kotanappapi.core.news.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record NewsListResponse(
        @Schema(description = "News' assigned id", example = "1")
        UUID id,

        @Schema(description = "News' title", example = "KOTAN GIRLS | ...)")
        String title,

        @Schema(description = "News' short description", example = "Dzisiaj cztery ...")
        String shortDescription,

        @Schema(description = "News' banner", example = "https://example.com/example.png")
        String banner,

        @Schema(description = "News' creation timestamp")
        Instant createdAt
) {
}
